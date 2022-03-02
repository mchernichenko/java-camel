package exercise;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

class App {
    public static void main(String[] args) throws Exception {

        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();

        // add routes
        camel.addRoutes(new HelloWorldRoute());

        // start is not blocking
        //camel.start();

        //camel.stop();

    }
}
