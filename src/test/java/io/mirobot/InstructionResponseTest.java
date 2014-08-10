package io.mirobot;

import java.util.UUID;

import junit.framework.TestCase;

import io.mirobot.instruction.Instruction;
import io.mirobot.instruction.InstructionResponse;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class InstructionResponseTest extends TestCase
{
   private String mirbotServerAddr = "ws://localhost:8899/";

   @Test
   public void testCreateCommandResponse()
   {
      UUID id = UUID.randomUUID();
      String response = "{\"status\":\"accepted\", \"id\":\"" + id.toString() + "\"}";
      InstructionResponse cr = new InstructionResponse(response);
      assertEquals(Instruction.State.ACCEPTED, cr.getState());
   }
}
