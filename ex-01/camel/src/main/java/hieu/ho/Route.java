package hieu.ho;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.dbcp.BasicDataSource;

public class Route extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    BasicDataSource ds1 = new BasicDataSource();
    ds1.setUrl("jdbc:postgresql://localhost:5432/postgres");
    ds1.setUsername("postgres");
    ds1.setPassword("postgres");
    ds1.setDriverClassName("org.postgresql.Driver");

    BasicDataSource ds2 = new BasicDataSource();
    ds2.setUrl("jdbc:postgresql://localhost:5555/postgres");
    ds2.setUsername("postgres");
    ds2.setPassword("postgres");
    ds2.setDriverClassName("org.postgresql.Driver");
    getContext().getRegistry().bind("dataSource1", ds1);
    getContext().getRegistry().bind("dataSource2", ds2);

    from("timer://hello?fixedRate=true&period=10000").to("direct:queryFromDb1");

    from("direct:queryFromDb1")
      .to(
        "sql:SELECT * FROM Table1?dataSource=#dataSource1&outputType=StreamList&outputClass=hieu.ho.CustomModel"
      )
      .to("log:stream")
      .split(body())
      .streaming()
      .to("log:row")
      .to("direct:insertToDb2")
      .end();

    from("direct:insertToDb2")
      .convertBodyTo(String.class)
      .log("logzzzzzz ${body}")
      .to(
        "sql:insert into table1 (body) values(:#${body}::jsonb)?dataSource=#dataSource2"
      );
  }
}
