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

package io.mirobot;

import java.net.URI;
import java.net.URISyntaxException;

import io.mirobot.connection.MirobotConnection;
import io.mirobot.connection.MirobotConnectionFactory;
import io.mirobot.pattern.Line;
import org.junit.Test;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class ExecutorTest
{
   private String mirbotServerAddr = "ws://192.168.0.10:8899/websocket";

   @Test
   public void testMultipleCommands() throws URISyntaxException, InterruptedException
   {
//      MirobotConnection connection = MirobotConnectionFactory.getConnection(new URI(mirbotServerAddr));
//
//      Line line = new Line(100);
//      connection.getExecutor().addDrawable(line);
//      Thread.sleep(100000);
   }
}
