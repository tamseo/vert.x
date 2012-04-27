/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.java.core.sockjs;

import org.vertx.java.core.Handler;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 *
 * This is an implementation of the server side part of <a href="https://github.com/sockjs">SockJS</a><p>
 *
 * <p>SockJS enables browsers to communicate with the server using a simple WebSocket-like api for sending
 * and receiving messages. Under the bonnet SockJS chooses to use one of several protocols depending on browser
 * capabilities and what apppears to be working across the network.<p>
 *
 * Available protocols include:<p>
 *
 * <ul>
 *   <li>WebSockets</li>
 *   <li>xhr-polling</li>
 *   <li>xhr-streaming</li>
 *   <li>json-polling</li>
 *   <li>event-source</li>
 *   <li>html-file</li>
 * </ul><p>
 *
 * This means, it should <i>just work</i> irrespective of what browser is being used, and whether there are nasty
 * things like proxies and load balancers between the client and the server.<p>
 *
 * For more detailed information on SockJS, see their website.<p>
 *
 * On the server side, you interact using instances of {@link SockJSSocket} - this allows you to send data to the
 * client or receive data via the {@link SockJSSocket#dataHandler}.<p>
 *
 * You can register multiple applications with the same SockJSServer, each using different path prefixes, each
 * application will have its own handler, and configuration as described by {@link AppConfig}<p>
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public interface SockJSServer {

  /**
   * Install an application
   * @param config The application configuration
   * @param sockHandler A handler that will be called when new SockJS sockets are created
   */
  void installApp(JsonObject config, final Handler<SockJSSocket> sockHandler);

  /**
   * Install an app which bridges the SockJS server to the event bus
   * @param sjsConfig The config for the app
   * @param permitted A list of JSON objects which define permitted matches
   */
  void bridge(JsonObject sjsConfig, JsonArray permitted);

  /**
   * Install an app which bridges the SockJS server to the event bus and which handles
   * client side login
   * @param sjsConfig The config for the app
   * @param permitted A list of JSON objects which define permitted matches
   * @param userCollection The name of the MongoDB collection which contains username/password information
   * @param persistorAddress Address on the event bus of a MongoDB persistor
   */
  void bridge(JsonObject sjsConfig, JsonArray permitted,
              String userCollection, String persistorAddress);

  /**
   * Install an app which bridges the SockJS server to the event bus and which handles
   * client side login
   * @param sjsConfig The config for the app
   * @param permitted A list of JSON objects which define permitted matches
   * @param userCollection The name of the MongoDB collection which contains username/password information
   * @param persistorAddress Address on the event bus of a MongoDB persistor
   * @param sessionTimeout Amount of time a login session will remain active
   */
  void bridge(JsonObject sjsConfig, JsonArray permitted,
              String userCollection, String persistorAddress, Long sessionTimeout);

  /**
   * Install an app which bridges the SockJS server to the event bus and which handles
   * client side login
   * @param sjsConfig The config for the app
   * @param permitted A list of JSON objects which define permitted matches
   * @param userCollection The name of the MongoDB collection which contains username/password information
   * @param persistorAddress Address on the event bus of a MongoDB persistor
   * @param sessionTimeout Amount of time a login session will remain active
   * @param loginAddress Address on the event bus where logins are handled
   * @param loginAddress Address on the event bus where logouts are handled
   */
  void bridge(JsonObject sjsConfig, JsonArray permitted,
              String userCollection, String persistorAddress, Long sessionTimeout,
              String loginAddress, String logoutAddress);

}
