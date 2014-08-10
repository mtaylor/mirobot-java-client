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

package io.mirobot.executor;

import java.util.concurrent.ConcurrentLinkedQueue;

import io.mirobot.instruction.Instruction;
import io.mirobot.connection.MirobotConnection;
import io.mirobot.instruction.InstructionResponse;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class SimpleThreadExecutor implements Runnable, Executor, MirobotConnection.MessageHandler
{
   private ConcurrentLinkedQueue<Drawable> drawableQueue;

   private ConcurrentLinkedQueue<Instruction> instructionQueue;

   private MirobotConnection connection;

   private boolean shutdownOnEmpty;

   public SimpleThreadExecutor(MirobotConnection connection)
   {
      shutdownOnEmpty = false;
      drawableQueue = new ConcurrentLinkedQueue<Drawable>();
      instructionQueue = new ConcurrentLinkedQueue<Instruction>();
      this.connection = connection;
      connection.addMessageHandler(this);
   }

   public void run()
   {
      while(true)
      {
         if(shutdownOnEmpty && drawableQueue.isEmpty() && instructionQueue.isEmpty())
         {
            break;
         }

         // FIXME Sending commands too quickly to Mirobot seems to cause a connection drop.
         try
         {
            Thread.sleep(1000);
         }
         catch (InterruptedException e)
         {
         }

         if (!drawableQueue.isEmpty() && instructionQueue.isEmpty())
         {
            drawNext(drawableQueue.poll());
            sendCommand(instructionQueue.peek());
         }

         if (!instructionQueue.isEmpty())
         {
            checkNextCommand();
         }
      }
   }

   private void drawNext(Drawable drawable)
   {
      for(Drawable d : drawable.draw())
      {
         if (d instanceof Instruction)
         {
            instructionQueue.add((Instruction) d);
            ((Instruction) d).setState(Instruction.State.QUEUED);
         }
         else
         {
            drawNext(d);
         }
      }
   }

   private void checkNextCommand()
   {
      if (instructionQueue.peek().getState() == Instruction.State.COMPLETE)
      {
         instructionQueue.remove();

         // If there are more commands on the drawableQueue then send next.
         if(!instructionQueue.isEmpty())
         {
            sendCommand(instructionQueue.peek());
         }
      }
   }

   public void addDrawable(Drawable e)
   {
      drawableQueue.add(e);
   }

   public void statusUpdate(InstructionResponse cr)
   {
      Instruction instruction = instructionQueue.peek();

      // FIXME Mirobot seems to return only the first 10 chars of the ID.  As temp hack we have removed ID checking
      //if(instruction.getId().equals(cr.getId()))
      //{
         instruction.setState(cr.getState());
      //}
   }

   public void onMessage(String message)
   {
      statusUpdate(new InstructionResponse(message));
   }

   private void sendCommand(Instruction instruction)
   {
      connection.sendMessage(instruction.toJson());
      instruction.setState(Instruction.State.SENT);
   }

   public synchronized void reset()
   {
      drawableQueue.clear();
      instructionQueue.clear();
   }

   public synchronized  void removeDrawable(Drawable drawable)
   {
      drawableQueue.remove(drawable);
   }

   public void shutdownWhenEmpty(boolean shutdownOnEmpty)
   {
      this.shutdownOnEmpty = shutdownOnEmpty;
   }
}
