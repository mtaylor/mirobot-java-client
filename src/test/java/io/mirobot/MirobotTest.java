package io.mirobot;

import javax.json.Json;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import io.mirobot.Exception.MirobotException;
import io.mirobot.connection.MirobotConnection;
import io.mirobot.connection.MirobotConnectionFactory;
import io.mirobot.pattern.Rectangle;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class MirobotTest extends TestCase
{
   private String mirbotServerAddr = "ws://192.168.0.10:8899/websocket";

   @Test
   public void testMirobotDrawing() throws URISyntaxException, MirobotException, InterruptedException
   {
//      Mirobot mirobot = new Mirobot(mirbotServerAddr);
//      mirobot.connect();
//
//      Rectangle rectangle = new Rectangle(50, 50);
//      mirobot.draw(rectangle);
//      Thread.sleep(1000000);
   }

   @Test
   public void testConnection() throws URISyntaxException
   {
//      URI mirobotServerUri = new URI(mirbotServerAddr);
//      MirobotConnection connection = MirobotConnectionFactory.getConnection(mirobotServerUri);
   }

   @Test
   public void testSendCommand() throws Exception
   {
//      MirobotConnection connection = MirobotConnectionFactory.getConnection(new URI(mirbotServerAddr));
//
//      List<String> expectedMessages = new ArrayList<String>();
//      expectedMessages.add("accepted");
//
//      TestMessageHandler handler = new TestMessageHandler(expectedMessages);
//      connection.addMessageHandler(handler);
//      connection.sendMessage(getMessage());
//
//      assertTrue(waitForCallbacks(10000, handler));
   }

   private static String getMessage()
   {
      return Json.createObjectBuilder()
         .add("cmd", "forward")
         .add("arg", "100")
         .add("id", "1")
         .build()
         .toString();
   }

   public boolean waitForCallbacks(long timeout, TestMessageHandler handler) throws InterruptedException
   {
      long startTime = System.currentTimeMillis();

      while(System.currentTimeMillis() < (startTime + timeout))
      {
         if(handler.isSuccess())
         {
            return true;
         }
         Thread.sleep(1000);
      }

      StringBuilder builder = new StringBuilder();
      builder.append("Callbacks not received: ");
      for(String m : handler.getExpectedMessages())
      {
         builder.append(m + ", ");
      }
      fail(builder.toString());
      return false;
   }
}
