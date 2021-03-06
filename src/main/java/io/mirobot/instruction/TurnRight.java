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

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class TurnRight extends AbstractInstruction
{
   private int degrees;

   public TurnRight(int degrees)
   {
      super(""+degrees, "right");
      this.degrees = degrees;
   }

   public int getDegrees()
   {
      return degrees;
   }

   public void setDegrees(int degrees)
   {
      this.degrees = degrees;
   }
}
