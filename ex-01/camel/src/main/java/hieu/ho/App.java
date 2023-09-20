package hieu.ho;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
public class App {


    private static final long DURATION_MILIS = 10000;

  public static void main(String[] args) throws Exception {
    System.out.println("Hello World!");
    try (CamelContext context = new DefaultCamelContext()) {
        context.addRoutes(new RouteException());
        context.start();
        Thread.sleep(DURATION_MILIS);
        context.stop();
    }
   
  }
}
