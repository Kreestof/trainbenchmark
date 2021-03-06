/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.jena.queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf.queries.RdfModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class JenaQuery<TPatternMatch extends JenaMatch> extends RdfModelQuery<TPatternMatch, JenaDriver> {

	protected Query jenaQuery;

	public JenaQuery(final JenaDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		super(driver, workspaceDir, query);
		this.jenaQuery = QueryFactory.read(queryPath);
	}
	
	public static <TPatternMatch extends JenaMatch> JenaQuery<TPatternMatch> create(final JenaDriver driver, final String workspaceDir, final RailwayQuery query)
			throws IOException {
		return new JenaQuery<TPatternMatch>(driver, workspaceDir, query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TPatternMatch> evaluate() throws IOException {
		final List<TPatternMatch> matches = new ArrayList<>();

		try (QueryExecution queryExecution = QueryExecutionFactory.create(jenaQuery, driver.getModel())) {
			final ResultSet resultSet = queryExecution.execSelect();

			while (resultSet.hasNext()) {
				final QuerySolution qs = resultSet.next();
				final JenaMatch match = JenaMatch.createMatch(query, qs);
				matches.add((TPatternMatch) match);
			}
		}

		return matches;
	}

}
