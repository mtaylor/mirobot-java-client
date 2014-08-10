package io.mirobot;


import java.net.URI;
import java.net.URISyntaxException;

import io.mirobot.Exception.MirobotException;
import io.mirobot.connection.MirobotConnection;
import io.mirobot.connection.MirobotConnectionFactory;
import io.mirobot.executor.Drawable;

/**
 * Mirobot Java Client
 *
 * @author <a href="mailto:mtaylor@redhat.com">Martyn Taylor</a>
 */

public class Mirobot
{
   public URI uri;

   public MirobotConnection connection;

   public Mirobot(String addr) throws URISyntaxException
   {
      this.uri = new URI(addr);
   }

   public void connect()
   {
      this.connection = MirobotConnectionFactory.getConnection(uri);
   }

   public void draw(Drawable e) throws MirobotException
   {
      if(connection == null)
      {
         throw new MirobotException("Mirobot is not connected");
      }
      connection.getExecutor().addDrawable(e);
   }

   public void reset()
   {
      connection.getExecutor().reset();
   }

   public void cancel(Drawable drawable)
   {
      connection.getExecutor().removeDrawable(drawable);
   }
   public void disconnect()
   {
      // TODO
   }

   public void shutdownWhenDrawingsComplete(boolean shutdown)
   {
      connection.getExecutor().shutdownWhenEmpty(shutdown);
   }
}
