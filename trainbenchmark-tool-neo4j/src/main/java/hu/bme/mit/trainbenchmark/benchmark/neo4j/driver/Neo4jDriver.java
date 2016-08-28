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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators.NodeComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jDriver extends Driver {

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected final Comparator<Node> nodeComparator = new NodeComparator();
	protected final File databaseDirectory;

	public Neo4jDriver(final String modelDir) throws IOException {
		super();
		this.databaseDirectory = new File(modelDir + "/neo4j-dbs/railway-database");
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		// delete old database directory
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	@Override
	public void beginTransaction() {
		tx = graphDb.beginTx();
	}

	@Override
	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	@Override
	public void read(final String modelPath) throws FileNotFoundException, XMLStreamException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databaseDirectory);

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(modelPath)),
					MapNodeCache.usingHashMap());
			tx.success();
		}
	}

	@Override
	public String getPostfix() {
		return ".graphml";
	}
	
	public Collection<Neo4jMatch> runQuery(final RailwayQuery query, final String queryDefinition, final Map<String, Object> parameters) throws IOException {
		final Collection<Neo4jMatch> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final Result executionResult = graphDb.execute(queryDefinition, parameters);
			while (executionResult.hasNext()) {
				final Map<String, Object> row = executionResult.next();
				results.add(Neo4jMatch.createMatch(query, row));
			}
		}

		return results;
	}
	
	public Collection<Neo4jMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		return runQuery(query, queryDefinition, Collections.emptyMap());
	}

	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

}
