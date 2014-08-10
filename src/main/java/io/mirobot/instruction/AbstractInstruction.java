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

package io.mirobot.instruction;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.mirobot.executor.Drawable;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public abstract class AbstractInstruction implements Instruction, Drawable
{
   private static int counter = 0;

   private UUID id;

   private State state;

   private String cmd;

   private String arg;

   private List<Drawable> drawables;

   public AbstractInstruction(String cmd)
   {
      id = UUID.randomUUID();
      state = State.NEW;
      drawables = new ArrayList<Drawable>();
      drawables.add(this);
      this.cmd = cmd;

   }

   public AbstractInstruction(String arg, String cmd)
   {
      id = UUID.randomUUID();
      state = State.NEW;
      drawables = new ArrayList<Drawable>();
      drawables.add(this);
      this.cmd = cmd;
      this.arg = arg;
   }

   @Override
   public String toJson()
   {
      JsonObjectBuilder builder = Json.createObjectBuilder()
                                    .add("cmd", cmd)
                                    .add("id", id.toString());
      if(arg != null)
      {
         builder.add("arg", arg);
      }
      return builder.build().toString();
   }

   @Override
   public State getState()
   {
      return state;
   }

   @Override
   public void setState(State state)
   {
      this.state = state;
   }

   @Override
   public List<Drawable> draw()
   {
      return drawables;
   }

   @Override
   public UUID getId()
   {
      return id;
   }
}
