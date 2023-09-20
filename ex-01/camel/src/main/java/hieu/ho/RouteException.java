package hieu.ho;

import org.apache.camel.builder.RouteBuilder;

class CustomGrpcError extends Exception {}

public class RouteException extends RouteBuilder {

  public static int count = 0;

  @Override
  public void configure() throws Exception {
    onException(CustomGrpcError.class)
      .maximumRedeliveries(100)
      .redeliveryDelay(0)
      .to("direct:exception");

    onException(Exception.class).log("other exception");

    from("timer://hello?fixedRate=true&period=10000").to("direct:start");

    from("direct:start")
      .routeId("testException")
      .doTry()
      .process(exchange -> {
        count++;
        log.info("Retry: " + count);
        // if condition is count < 200, the route will be directed to direct:exception because it's just retry 100 times
        if (count < 50) {
          throw new NullPointerException("null pointer exception");
        }
      })
      // IF throw exception in try block, it'll retry the whole block
      // .log("count > 50?")
      // .throwException(new Exception())

      // IF helloroute throw exception, it can't be caught by doCatch
      // .to("direct:helloroute")
      .to("direct:success")
      .doCatch(Exception.class)
      .throwException(new CustomGrpcError())
      .end()
      .throwException(new Exception());

    from("direct:exception").log("log failed");
    from("direct:success").log("log success");
    from("direct:helloroute")
      .log("log helloroute")
      .throwException(new Exception());
  }
}
