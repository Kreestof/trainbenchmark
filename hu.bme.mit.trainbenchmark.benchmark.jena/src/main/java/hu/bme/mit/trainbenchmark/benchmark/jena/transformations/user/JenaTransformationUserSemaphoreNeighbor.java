/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Resource;

public class JenaTransformationUserSemaphoreNeighbor extends JenaTransformationUser {

	public JenaTransformationUserSemaphoreNeighbor(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> routes) throws IOException {
		jenaDriver.deleteSingleOutgoingEdge(routes, ROUTE, ENTRY);
	}

}
