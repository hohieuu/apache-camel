package hieu.ho;

import org.apache.camel.main.Main;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main main = new Main();
        main.configure().addRoutesBuilder(new Route());
        try {
            main.run(args);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}