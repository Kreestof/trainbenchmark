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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair;

import java.util.Collection;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCoreTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4jCoreTransformationRepairSemaphoreNeighbor extends Neo4jCoreTransformation<Neo4jSemaphoreNeighborMatch> {

	public Neo4jCoreTransformationRepairSemaphoreNeighbor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jSemaphoreNeighborMatch> matches) {
		for (final Neo4jSemaphoreNeighborMatch snm : matches) {
			final Node semaphore = snm.getSemaphore();
			final Node route2 = snm.getRoute2();
			route2.createRelationshipTo(semaphore, Neo4jConstants.relationshipTypeEntry);
		}
	}

}
