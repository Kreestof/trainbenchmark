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

package hu.bme.mit.trainbenchmark.benchmark.test;

import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.CONNECTEDSEGMENTS;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.POSLENGTH;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.ROUTESENSOR;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SEMAPHORENEIGHBOR;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SWITCHMONITORED;
import static hu.bme.mit.trainbenchmark.constants.RailwayQuery.SWITCHSET;
import static hu.bme.mit.trainbenchmark.constants.ScenarioEnum.MINIMAL;

import org.junit.Test;

public abstract class MinimalTest extends TrainBenchmarkTest {

	@Test
	public void connectedSegments() throws Exception {
		testQuery(CONNECTEDSEGMENTS, MINIMAL, 1);
	}

	@Test
	public void posLength() throws Exception {
		testQuery(POSLENGTH, MINIMAL, 1);
	}

	@Test
	public void routeSensor() throws Exception {
		testQuery(ROUTESENSOR, MINIMAL, 1);
	}

	@Test
	public void semaphoreNeighbor() throws Exception {
		testQuery(SEMAPHORENEIGHBOR, MINIMAL, 1);
	}

	@Test
	public void switchSensor() throws Exception {
		testQuery(SWITCHMONITORED, MINIMAL, 1);
	}

	@Test
	public void switchSet() throws Exception {
		testQuery(SWITCHSET, MINIMAL, 1);
	}

}