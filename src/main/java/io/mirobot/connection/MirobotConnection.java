/*
 * Copyright 2005-2014 Red Hat, Inc.
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.mirobot.connection;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.Map;

import io.mirobot.executor.Executor;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

@ClientEndpoint
public class MirobotConnection
{
   private Session userSession = null;

   private MessageHandler messageHandler;

   private Map<URI, MirobotConnection> connections;

   private Executor executor;

   protected MirobotConnection(URI uri)
   {
      try
      {
         WebSocketContainer container = ContainerProvider.getWebSocketContainer();
         container.connectToServer(this, uri);
      }
      catch(Exception e)
      {
         throw new RuntimeException(e);
      }
   }

   public void setExecutor(Executor executor)
   {
      this.executor = executor;
   }

   /**
    * Callback hook for Connection open events.
    *
    * @param userSession
    *            the userSession which is opened.
    */
   @OnOpen
   public void onOpen(Session userSession) {
      this.userSession = userSession;
   }

   @OnClose
   public void onClose(Session userSession, CloseReason reason) {
      this.userSession = null;
   }

   @OnMessage
   public void onMessage(String message) {
      if (this.messageHandler != null)
         this.messageHandler.onMessage(message);
   }

   public void addMessageHandler(MessageHandler msgHandler) {
      this.messageHandler = msgHandler;
   }


   public void sendMessage(String message) {
      this.userSession.getAsyncRemote().sendText(message);
   }

   /**
    * Message handler.
    */
   public static interface MessageHandler {
      public void onMessage(String message);
   }

   public Executor getExecutor()
   {
      return executor;
   }
}
