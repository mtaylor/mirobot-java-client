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

import java.net.URISyntaxException;

import io.mirobot.Exception.MirobotException;
import io.mirobot.Mirobot;
import io.mirobot.instruction.MoveForward;
import io.mirobot.instruction.TurnLeft;
import io.mirobot.pattern.Rectangle;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class Example
{
   public static void main(String args[]) throws URISyntaxException, MirobotException, InterruptedException
   {
      // The URI to the WebSocket server running on Mirobot.
      String mirobotAddress = "ws://192.168.0.10:8899/websocket";

      // Create a new instance of Mirobot
      Mirobot miro = new Mirobot(mirobotAddress);

      // Create new connection to Mirobot.  Connections are cached and limited to 1 connection per Miro to avoid instruction interleaving.
      miro.connect();

      // Create simple instructions to move mirobot.
      MoveForward forward = new MoveForward(50);
      TurnLeft turnLeft = new TurnLeft(180);

      // The draw method will execute instructions.
      miro.draw(forward);
      miro.draw(turnLeft);

      // You can create more complex objects see implementations of Line, Rectangle, Square
      Rectangle rectangle = new Rectangle(100, 50);

      // Use the draw method.  The Mirobot client converts complex objects to instructions and queues them locally,
      miro.draw(rectangle);

      // Tells the Mirobot client to shutdown once it has completed all jobs in it's queue.
      miro.shutdownWhenDrawingsComplete(true);
   }
}
