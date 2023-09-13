package hieu.ho;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.BindToRegistry;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class Route extends RouteBuilder {

    @BindToRegistry("dataSource")
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        return ds;
    }


    // @BindToRegistry("dataSource2")
    // public DataSource dataSource2() {
    //     BasicDataSource ds = new BasicDataSource();
    //     ds.setUrl("jdbc:postgresql://localhost:5433/postgres");
    //     ds.setUsername("postgres");
    //     ds.setPassword("postgres");
    //     ds.setDriverClassName("org.postgresql.Driver");
    //     return ds;
    // }

    @Override
    public void configure() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        from("timer://hello?fixedRate=true&period=1000")
                .to("sql:select 1");

    }
}