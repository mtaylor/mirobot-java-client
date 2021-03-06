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

package io.mirobot.pattern;

import java.util.ArrayList;
import java.util.List;

import io.mirobot.instruction.Instruction;
import io.mirobot.executor.Drawable;
import io.mirobot.instruction.TurnLeft;

/**
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class Rectangle implements Drawable
{
   private int width;

   private int length;

   public Rectangle(int width, int length)
   {
      this.width = width;
      this.length = length;
   }

   public List<Drawable> draw()
   {
      List<Drawable> drawables = new ArrayList<Drawable>();
      drawables.add(new Line(width));
      drawables.add(new TurnLeft(90));
      drawables.add(new Line(length));
      drawables.add(new TurnLeft(90));
      drawables.add(new Line(width));
      drawables.add(new TurnLeft(90));
      drawables.add(new Line(length));
      drawables.add(new TurnLeft(90));
      return drawables;
   }
}
