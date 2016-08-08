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
package hu.bme.mit.trainbenchmark.benchmark.sql.matches;

import java.sql.ResultSet;
import java.sql.SQLException;

import hu.bme.mit.trainbenchmark.constants.QueryConstants;

public class SqlVertexMatch extends SqlMatch {

	public SqlVertexMatch(final ResultSet rs) throws SQLException {
		match = new Long[] { rs.getLong(QueryConstants.VAR_VERTEX) };
	}

	public Long getVertex() {
		return match[0];
	}

}
