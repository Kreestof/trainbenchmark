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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.util.Bag;
import org.eclipse.ocl.util.Tuple;

public class EclipseOCLPosLengthChecker extends EclipseOCLChecker<EMFPosLengthMatch> {

	public EclipseOCLPosLengthChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super(driver, bc);
	}

	@Override
	protected EClassifier getContext() {
		return RailwayPackage.eINSTANCE.getSegment();
	}

	@Override
	public Collection<EMFPosLengthMatch> check() {
		matches = new ArrayList<>();

		final Object evaluate = queryEvaluator.evaluate(driver.getContainer());
		final Bag bag = (Bag) evaluate;
		for (final Object object : bag) {
			final Tuple tuple = (Tuple) object;
			final Object segment = tuple.getValue("segment");
			matches.add(new EMFPosLengthMatch((Segment) segment));
		}

		return matches;
	}

}
