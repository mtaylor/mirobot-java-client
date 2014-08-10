Mirobot Java Client
====================

A Java client for controlling a Mirobot.  See http://mirobot.io for info on Mirobot.

Build Instructions
-------------------


Install Maven.  See: http://maven.apache.org/


1. cd mirobot-client directory;
2. mvn clean install

Maven Deps
-----------

Add the following deps to your pom.xml

```xml
   <dependencies>
      <dependency>
         <groupId>javax.websocket</groupId>
         <artifactId>javax.websocket-client-api</artifactId>
         <version>1.0</version>
      </dependency>
      <dependency>
         <groupId>org.glassfish.tyrus</groupId>
         <artifactId>tyrus-client</artifactId>
         <version>1.1</version>
      </dependency>
      <dependency>
         <groupId>org.glassfish.tyrus</groupId>
         <artifactId>tyrus-container-grizzly</artifactId>
         <version>1.1</version>
      </dependency>
      <dependency>
         <groupId>javax.json</groupId>
         <artifactId>javax.json-api</artifactId>
         <version>1.0</version>
      </dependency>
      <dependency>
         <groupId>org.glassfish</groupId>
         <artifactId>javax.json</artifactId>
         <version>1.0.1</version>
      </dependency>
      <dependency>
         <groupId>io.mirobot</groupId>
         <artifactId>mirobot-client</artifactId>
         <version>1.0-SNAPSHOT</version>
      </dependency>
   </dependencies>
</project>
```

Example Usage
--------------

```java
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
```
