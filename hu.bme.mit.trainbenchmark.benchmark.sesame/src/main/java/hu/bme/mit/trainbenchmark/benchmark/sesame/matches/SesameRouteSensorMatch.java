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
package hu.bme.mit.trainbenchmark.benchmark.sesame.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;

public class SesameRouteSensorMatch extends SesameMatch implements RouteSensorMatch {

	public SesameRouteSensorMatch(final BindingSet bs) {
		super(bs);
	}

	@Override
	public Value getRoute() {
		return bs.getValue(QueryConstants.VAR_ROUTE);
	}

	@Override
	public Value getSensor() {
		return bs.getValue(QueryConstants.VAR_SENSOR);
	}

	@Override
	public Value getSwP() {
		return bs.getValue(QueryConstants.VAR_SWP);
	}

	@Override
	public Value getSw() {
		return bs.getValue(QueryConstants.VAR_SW);
	}

	@Override
	public Value[] toArray() {
		return new Value[] { getRoute(), getSensor(), getSwP(), getSw() };
	}

}
