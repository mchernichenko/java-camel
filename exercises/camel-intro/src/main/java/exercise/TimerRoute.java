package exercise;

import org.apache.camel.builder.RouteBuilder;

public class TimerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // по умолчанию сообщение генерится раз в 1 секунду. foo - произвольное имя таймера
        from("timer:foo") // from - метод RouteBuilder
                .log(">>>>>>>>> Hello Camel <<<<<<<<<<<");
    }
}
