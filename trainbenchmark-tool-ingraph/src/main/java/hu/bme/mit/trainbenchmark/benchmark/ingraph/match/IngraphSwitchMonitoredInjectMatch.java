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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredInjectMatch;
import scala.collection.immutable.Map;

public class IngraphSwitchMonitoredInjectMatch extends IngraphMatch implements SwitchMonitoredInjectMatch {

	public IngraphSwitchMonitoredInjectMatch(final Map<Object, Object> qs) {
		super(qs);
	}

	@Override
	public Long getSw() {
		return (Long) qs.get(0).get();
	}

}
