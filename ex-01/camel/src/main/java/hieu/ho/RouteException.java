package hieu.ho;

import org.apache.camel.builder.RouteBuilder;

public class RouteException extends RouteBuilder {

  public static int count = 0;

  @Override
  public void configure() throws Exception {
          onException(Exception.class)
      .maximumRedeliveries(100)
      .redeliveryDelay(0)
      .to("direct:exception");

    from("timer://hello?fixedRate=true&period=10000").to("direct:start");

    from("direct:start")
      .routeId("testException")
      .process(exchange -> {
        count++;
        log.info("COUNT: " + count);
        if (count < 50) {
          throw new NullPointerException("null pointer exception");
        }
      })
      .to("direct:success");

    from("direct:exception").log("log failed");
    from("direct:success").log("log success");

  }
}
