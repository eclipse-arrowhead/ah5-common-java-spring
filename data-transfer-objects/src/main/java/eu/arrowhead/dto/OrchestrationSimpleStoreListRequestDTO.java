package eu.arrowhead.dto;

import java.util.List;

public record OrchestrationSimpleStoreListRequestDTO(
		List<OrchestrationSimpleStoreRequestDTO> candidates) {
}
