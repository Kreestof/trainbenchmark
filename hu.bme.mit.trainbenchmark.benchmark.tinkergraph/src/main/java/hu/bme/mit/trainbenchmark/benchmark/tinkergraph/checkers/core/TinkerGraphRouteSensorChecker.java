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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers.TinkerGraphChecker;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.util.TinkerGraphUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

public class TinkerGraphRouteSensorChecker extends TinkerGraphChecker<TinkerGraphRouteSensorMatch> {

	public TinkerGraphRouteSensorChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphRouteSensorMatch> check() {
		final Collection<TinkerGraphRouteSensorMatch> matches = new ArrayList<>();

		final Collection<Vertex> routes = driver.collectVertices(ModelConstants.ROUTE);

		for (final Vertex route : routes) {
			// (route:Route)-[:follows]->()
			final Iterable<Vertex> swPs = TinkerGraphUtil.getAdjacentNodes(route, ModelConstants.FOLLOWS, Direction.OUT, ModelConstants.SWITCHPOSITION);
			for (final Vertex swP : swPs) {
				// (swP:switchPosition)-[:target]->()
				final Iterable<Vertex> sws = TinkerGraphUtil.getAdjacentNodes(swP, ModelConstants.TARGET, Direction.OUT, ModelConstants.SWITCH);
				for (final Vertex sw : sws) {
					// (switch:Switch)-[:sensor]->()
					final Iterable<Vertex> sensors = TinkerGraphUtil.getAdjacentNodes(sw, ModelConstants.MONITORED_BY, Direction.OUT, ModelConstants.SENSOR);
					for (final Vertex sensor : sensors) {
						// (sensor:Sensor)<-[:gathers]-(Route) NAC
						if (!TinkerGraphUtil.isConnected(route, sensor, ModelConstants.GATHERS)) {
							final Map<String, Object> match = new HashMap<>();
							match.put(QueryConstants.VAR_ROUTE, route);
							match.put(QueryConstants.VAR_SENSOR, sensor);
							match.put(QueryConstants.VAR_SWP, swP);
							match.put(QueryConstants.VAR_SW, sw);
							matches.add(new TinkerGraphRouteSensorMatch(match));
						}
					}
				}
			}
		}

		return matches;
	}
}
