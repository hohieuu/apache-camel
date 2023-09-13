# Ex 1 
```
Create camel project for get from database and insert to a database.
In process, change data from source db to json and insert to body of destination db
Example:
Db1:
Table1:  id, name, document
DB2:
Table1: id, body
Body field will contain data of a record in table1 with format json
```


STEP 1:
Create a docker file with java 19, maven, camel

STEP 2:
Init a camel project with maven

STEP 3:
Create a route to get data from db1 and insert to db2

STEP 4:
Create a route to get data from db2 and insert to db1

STEP 5:
Create a route to get data from db1 and insert to db2 with json format


```
# camel project
1. ADD DEPENDENCIES:
* Create a pom.xml file
2. CREATE ROUTE:
* Create a route class
(
    <!-- import from camel core -->
    name: Route1.java
    path: src/main/java/com/example/demo/route
    code:
        public class Route1 extends RouteBuilder {
            @Override
            public void configure() throws Exception {
                from("timer:foo?period=5000")
                .setBody().simple("Hello World Camel fired at ${header.firedTime}")
                .to("stream:out");
            }
        }
)
3. CREATE MAIN CLASS:
* Create a main class simple to run the route
(
    name: DemoApplication.java
    path: src/main/java/com/example/demo
    code:
        public class DemoApplication {
            public static void main(String[] args) {
                // run route
                Main main = new Main();
                main.addRouteBuilder(new Route1());
                main.run(args);
            }
        }
)

4. GET dependencies:
* Run command: mvn dependency:tree
* Copy dependencies to pom.xml
* Run command: mvn clean install
* Run command: mvn clean package
5. java -jar target/demo-0.0.1-SNAPSHOT.jar
6. RUN 





```




# HOW TO RUN
```docker-compose up -d```
# HOW TO STOP
```docker-compose down```