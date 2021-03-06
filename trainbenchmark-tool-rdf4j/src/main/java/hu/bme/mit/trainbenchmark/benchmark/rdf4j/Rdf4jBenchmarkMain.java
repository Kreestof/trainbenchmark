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

package hu.bme.mit.trainbenchmark.benchmark.rdf4j;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.config.Rdf4jBenchmarkConfig;

public class Rdf4jBenchmarkMain {

	public static void main(final String[] args) throws Exception {
		final Rdf4jBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], Rdf4jBenchmarkConfig.class);
		final Rdf4jBenchmarkScenario scenario = new Rdf4jBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}
