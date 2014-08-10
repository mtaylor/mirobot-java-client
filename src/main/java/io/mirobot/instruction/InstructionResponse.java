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
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.UUID;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class InstructionResponse
{
   private UUID id;

   private Instruction.State state;

   public InstructionResponse(String response)
   {
      JsonReader jsonReader = Json.createReader(new StringReader(response));
      JsonObject object = jsonReader.readObject();
      this.state = Instruction.State.valueOf(object.getString("status").toUpperCase());
      //this.id = UUID.fromString(object.getString("id"));
   }

   public UUID getId()
   {
      return id;
   }

   public Instruction.State getState()
   {
      return state;
   }
}
