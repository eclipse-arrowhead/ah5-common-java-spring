package eu.arrowhead.dto;

import java.util.Map;

public record QoSPreferencesDTO(
			String type,
			String operation,
			Map<String, Object> requirements
		) {

}
