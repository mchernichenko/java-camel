package exercise;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

class App {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new HelloWorldRoute());

    }
}
