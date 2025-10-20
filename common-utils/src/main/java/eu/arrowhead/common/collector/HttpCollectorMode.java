/*******************************************************************************
 *
 * Copyright (c) 2025 AITIA
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 *
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  	AITIA - implementation
 *  	Arrowhead Consortia - conceptualization
 *
 *******************************************************************************/
package eu.arrowhead.common.collector;

public enum HttpCollectorMode {
	SR_ONLY, SR_AND_ORCH;

	//=================================================================================================
	// members

	public static final String SR_AND_ORCH_VALUE = "SR_AND_ORCH"; // right side must be a constant expression
}