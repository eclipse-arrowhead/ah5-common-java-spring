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

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import eu.arrowhead.common.Constants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.exception.ArrowheadException;
import eu.arrowhead.common.model.ServiceModel;
import eu.arrowhead.common.service.validation.name.InterfaceTemplateNameNormalizer;
import eu.arrowhead.common.service.validation.name.ServiceDefinitionNameNormalizer;
import eu.arrowhead.common.service.validation.name.SystemNameNormalizer;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

@Service
public class ServiceCollector {

	//=================================================================================================
	// members

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ICollectorDriver driver;

	@Autowired
	private ServiceDefinitionNameNormalizer serviceDefNameNormalizer;

	@Autowired
	private InterfaceTemplateNameNormalizer interfaceTemplateNameNormalizer;

	@Autowired
	private SystemNameNormalizer systemNameNormalizer;

	@Resource(name = Constants.ARROWHEAD_CONTEXT)
	private Map<String, Object> arrowheadContext;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@Nullable
	public ServiceModel getServiceModel(final String serviceDefinition, final String templateName, final String providerName) throws ArrowheadException {
		logger.debug("getServiceModel started...");
		Assert.isTrue(!Utilities.isEmpty(serviceDefinition), "service definition is empty");
		Assert.isTrue(!Utilities.isEmpty(templateName), "template name is empty");

		final String nServiceDefinition = serviceDefNameNormalizer.normalize(serviceDefinition);

		final String key = Constants.KEY_PREFIX_FOR_SERVICE_MODEL + nServiceDefinition;
		if (!arrowheadContext.containsKey(key)) {
			final String nTemplateName = interfaceTemplateNameNormalizer.normalize(templateName);
			final String nProviderName = !Utilities.isEmpty(providerName) ? systemNameNormalizer.normalize(providerName) : null;

			final ServiceModel model = driver.acquireService(nServiceDefinition, nTemplateName, nProviderName);
			if (model != null) {
				arrowheadContext.put(key, model);
			}
		}

		return (ServiceModel) arrowheadContext.get(key);
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	@PostConstruct
	private void init() throws ArrowheadException {
		driver.init();
	}
}