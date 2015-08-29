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

package hu.bme.mit.trainbenchmark.generator.concretes.schedule.scales;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.DESTINATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.NEIGHBORS;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.concretes.schedule.HeterogeneousScheduleGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.util.Node;

import java.io.IOException;
import java.util.ArrayList;

public class HeterogeneousScaleFreeGenerator extends HeterogeneousScheduleGenerator implements ScaleFreeModel {

	protected ScaleFreeGenerator sfg;

	public HeterogeneousScaleFreeGenerator(FormatGenerator formatGenerator,
			GeneratorConfig generatorConfig) {
		super(formatGenerator, generatorConfig);
	}

	@Override
	protected void initializeConstants() {
		super.initializeConstants();
		sfg = new ScaleFreeGenerator(this);
		sfg.initializeConstants();
	}

	@Override
	protected void printMessage() {
		System.out.print("Generate heterogeneous scale-free model...");

	}

	@Override
	protected void initializationStep() throws IOException {
		sfg.initializationStep();
	}

	@Override
	protected void generateStations() throws IOException {
		if (stations.size() < maxNumberOfStations) {
			addStation();
			sfg.newStationConnections(NEIGHBORS, lastSt(), getNeighborsNumber());
		}
	}

	@Override
	protected void generateSchedules() throws IOException {
		addSchedule();
		int destinations = getDestinationsNumber();
		if (currentNodes < maxNumberOfSchedules - maxNumberOfRepetitiveSchedules) {
			if (!schedulesOfDestinations.containsKey(destinations)) {
				schedulesOfDestinations.put(destinations, new ArrayList<Integer>());
			}
			schedulesOfDestinations.get(destinations).add(lastSch());
			sfg.newStationConnections(DESTINATIONS, lastSch(), destinations);
		} else {
			addRepetitiveScheduleConnections(lastSch(), destinations);
		}

	}

	@Override
	protected int getNeighborsNumber() {
		return 0;
	}

	@Override
	public ArrayList<Node> getStations() {
		return stations;
	}

	@Override
	public ArrayList<Node> getSchedules() {
		return schedules;
	}

	@Override
	public void addNewStation() throws IOException {
		addStation();
	}

}