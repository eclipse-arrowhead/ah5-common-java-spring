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
package eu.arrowhead.common;

import eu.arrowhead.common.collector.HttpCollectorMode;
import eu.arrowhead.common.http.filter.authentication.AuthenticationPolicy;
import eu.arrowhead.common.http.filter.authorization.ManagementPolicy;
import eu.arrowhead.common.service.normalization.NormalizationMode;
import eu.arrowhead.dto.DTODefaults;

public class Defaults {

	//=================================================================================================
	// members

	// Global

	public static final String MAX_PAGE_SIZE_DEFAULT = "1000";
	public static final String NORMALIZATION_MODE_DEFAULT = NormalizationMode.EXTENDED_VALUE;
	public static final String DEFAULT_CLOUD = DTODefaults.DEFAULT_CLOUD;
	public static final String DEFAULT_AUTHORIZATION_SCOPE = DTODefaults.DEFAULT_AUTHORIZATION_SCOPE;

	// System related

	public static final String SERVER_ADDRESS_DEFAULT = "";
	public static final String SERVER_PORT_DEFAULT = "0"; // just to avoid NullPointerException
	public static final String DOMAIN_NAME_DEFAULT = "";
	public static final String SERVICE_REGISTRY_ADDRESS_DEFAULT = Constants.LOCALHOST;
	public static final String SERVICE_REGISTRY_PORT_DEFAULT = "8443";
	public static final String AUTHENTICATION_POLICY_DEFAULT = AuthenticationPolicy.CERTIFICATE_VALUE;
	public static final String ENABLE_MANAGEMENT_FILTER_DEFAULT = "false";
	public static final String MANAGEMENT_POLICY_DEFAULT = ManagementPolicy.SYSOP_ONLY_VALUE;
	public static final String MANAGEMENT_WHITELIST_DEFAULT = "\"\"";
	public static final String ALLOW_SELF_ADDRESSING_DEFAULT = "true";
	public static final String ALLOW_NON_ROUTABLE_ADDRESSING_DEFAULT = "true";
	public static final String HTTP_COLLECTOR_MODE_DEFAULT = HttpCollectorMode.SR_AND_ORCH_VALUE;
	public static final String ENABLE_BLACKLIST_FILTER_DEFAULT = "false";
	public static final String FORCE_BLACKLIST_FILTER_DEFAULT = "true";
	public static final String SERVICE_ADDRESS_ALIAS_DEFAULT = "\"\"";
	public static final String BLACKLIST_CHECK_EXCLUDE_LIST_DEFAULT = Constants.SYS_NAME_SERVICE_REGISTRY + ","
			+ Constants.SYS_NAME_AUTHENTICATION + ","
			+ Constants.SYS_NAME_CONSUMER_AUTHORIZATION + ","
			+ Constants.SYS_NAME_DYNAMIC_SERVICE_ORCHESTRATION;

	// SSL related

	public static final String SERVER_SSL_ENABLED_DEFAULT = "false";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_KEY__STORE__TYPE_DEFAULT = Constants.PKCS12;
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_KEY__STORE_DEFAULT = "";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_KEY__STORE__PASSWORD_DEFAULT = "";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_KEY__PASSWORD_DEFAULT = "";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_KEY__ALIAS_DEFAULT = "";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_TRUST__STORE_DEFAULT = "";
	@SuppressWarnings("checkstyle:ConstantName")
	public static final String SERVER_SSL_TRUST__STORE__PASSWORD_DEFAULT = "";
	public static final String DISABLE_HOSTNAME_VERIFIER_DEFAULT = "false";

	// HTTP related

	public static final String HTTP_CLIENT_CONNECTION_TIMEOUT_DEFAULT = "30000";
	public static final String HTTP_CLIENT_SOCKET_TIMEOUT_DEFAULT = "30000";
	public static final String LOG_ALL_REQUEST_AND_RESPONSE_DEFAULT = "false";
	public static final String CORS_ORIGIN_PATTERN_DEFAULT = "*";

	// MQTT related

	public static final String MQTT_API_ENABLED_DEFAULT = "false";
	public static final String MQTT_BROKER_ADDRESS_DEFAULT = "";
	public static final String MQTT_BROKER_PORT_DEFAULT = "1883";
	public static final String MQTT_CLIENT_PASSWORD_DEFAULT = "";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	protected Defaults() {
		throw new UnsupportedOperationException();
	}
}