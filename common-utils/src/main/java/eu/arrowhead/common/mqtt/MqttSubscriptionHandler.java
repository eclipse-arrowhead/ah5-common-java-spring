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
package eu.arrowhead.common.mqtt;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import eu.arrowhead.common.exception.ExternalServerError;

public class MqttSubscriptionHandler {

	//=================================================================================================
	// members

	private final String connectionId;

	private final MqttClient client;

	private final Map<String, LinkedBlockingQueue<MqttMessage>> topicQueueMap = new ConcurrentHashMap<>();

	private final Logger logger = LogManager.getLogger(getClass());

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public MqttSubscriptionHandler(final String connectionId, final MqttClient client) {
		this.connectionId = connectionId;
		this.client = client;

		if (client == null || !client.isConnected()) {
			throw new ExternalServerError("Cannot initialize MqttSubscriptionHandler, because client is not connected");
		}
		client.setCallback(createMqttCallback(client.getServerURI()));
	}

	//-------------------------------------------------------------------------------------------------
	public String getConnectionId() {
		return connectionId;
	}

	//-------------------------------------------------------------------------------------------------
	public LinkedBlockingQueue<MqttMessage> addSubscription(final String topic) throws MqttException {
		logger.debug("addSubscription ...");

		client.subscribe(topic);
		topicQueueMap.putIfAbsent(topic, new LinkedBlockingQueue<>());
		return topicQueueMap.get(topic);
	}

	//-------------------------------------------------------------------------------------------------
	public void removeSubscription(final String topic) throws MqttException {
		logger.debug("removeSubscription ...");

		client.unsubscribe(topic);
		if (topicQueueMap.containsKey(topic)) {
			topicQueueMap.get(topic).clear();
			topicQueueMap.remove(topic);
		}
	}

	//-------------------------------------------------------------------------------------------------
	public Set<String> getSubscribedTopics() {
		logger.debug("getSubscribedTopics started...");
		return topicQueueMap.keySet();
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private MqttCallback createMqttCallback(final String brokerUri) {
		return new MqttCallback() {

			//-------------------------------------------------------------------------------------------------
			@Override
			public void messageArrived(final String topic, final MqttMessage message) throws Exception {
				logger.debug("MQTT message arrived to service topic: " + topic);

				topicQueueMap.get(topic).add(message);
			}

			//-------------------------------------------------------------------------------------------------
			@Override
			public void deliveryComplete(final IMqttDeliveryToken token) {
				logger.debug("MQTT message delivered to broker " + brokerUri + ". Topic(s): " + token.getTopics());
			}

			//-------------------------------------------------------------------------------------------------
			@Override
			public void connectionLost(final Throwable cause) {
				logger.error("MQTT Broker connection lost: " + brokerUri + ". Reason: " + cause.getMessage());
			}
		};
	}
}