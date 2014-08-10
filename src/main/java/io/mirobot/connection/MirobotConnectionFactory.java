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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import io.mirobot.executor.Executor;
import io.mirobot.executor.ExecutorFactory;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class MirobotConnectionFactory
{
   private static Map<URI, MirobotConnection> connections;

   static
   {
     connections = new HashMap<URI, MirobotConnection>();
   }

   private MirobotConnectionFactory() {};

   public static MirobotConnection getConnection(URI uri)
   {
      if(connections.containsKey(uri))
      {
         return connections.get(uri);
      }
      else
      {
         MirobotConnection connection = new MirobotConnection(uri);
         Executor e = ExecutorFactory.createExecutor(connection);
         connections.put(uri, connection);
         return connection;
      }
   }
}
