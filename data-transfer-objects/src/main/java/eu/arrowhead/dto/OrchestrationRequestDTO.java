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
package eu.arrowhead.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record OrchestrationRequestDTO(
		OrchestrationServiceRequirementDTO serviceRequirement,
		Map<String, Boolean> orchestrationFlags,
		Map<String, String> qosRequirements,
		Integer exclusivityDuration) {

	//=================================================================================================
	// nested classes

	//-------------------------------------------------------------------------------------------------
	public static class Builder {

		//=================================================================================================
		// members

		private OrchestrationServiceRequirementDTO serviceRequirement;
		private Map<String, Boolean> orchestrationFlags;
		private Map<String, String> qosRequirements;
		private Integer exclusivityDuration;

		//=================================================================================================
		// methods

		//-------------------------------------------------------------------------------------------------
		public Builder serviceRequirement(final OrchestrationServiceRequirementDTO serviceRequirement) {
			this.serviceRequirement = serviceRequirement;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder orchestrationFlags(final Map<String, Boolean> orchestrationFlags) {
			this.orchestrationFlags = orchestrationFlags;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder orchestrationFlag(final String orchestrationFlag, final boolean value) {
			if (this.orchestrationFlags == null) {
				this.orchestrationFlags = new HashMap<>();
			}
			this.orchestrationFlags.put(orchestrationFlag, value);
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder qosRequirements(final Map<String, String> qosRequirements) {
			this.qosRequirements = qosRequirements;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder qosRequirement(final String key, final String value) {
			if (this.qosRequirements == null) {
				this.qosRequirements = new HashMap<>();
			}
			this.qosRequirements.put(key, value);
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public Builder exclusivityDuration(final Integer exclusivityDuration) {
			this.exclusivityDuration = exclusivityDuration;
			return this;
		}

		//-------------------------------------------------------------------------------------------------
		public OrchestrationRequestDTO build() {
			return new OrchestrationRequestDTO(serviceRequirement, orchestrationFlags, qosRequirements, exclusivityDuration);
		}
	}
}
