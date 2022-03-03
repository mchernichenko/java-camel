package exercise;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.nio.file.Paths;

class App {
    public static void main(String[] args) throws Exception {

        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();

        // add routes
        camel.addRoutes(new HelloWorldRoute());
        // start is not blocking
        camel.start();
        camel.stop();

        // вывод сообщений по таймеру
        camel.addRoutes(new TimerRoute());
        camel.start();
        Thread.sleep(2_000);
        camel.stop();

        // пример обработки файлов
        copyFiles();
    }

    //
    public static void copyFiles() throws Exception {
        System.out.println("Текущая папка: " + Paths.get(".").toRealPath().toString());

        CamelContext camel = new DefaultCamelContext();
        // указываем Camel, где найти файл с настройками application.properties. В отличие от Spring,
        // у camel нет дефалтового пути к application.properties
        camel.getPropertiesComponent().setLocation("classpath:application.properties");

        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // noop - копирование, а не перемещение. Путь можно указывать абсолютный или относительный
                // в {{ }} указывается параметр из application.properties
                from("file:{{in_folder}}?noop=true")
                        .routeId("File processing") // просто Id, может быть любым, удобно задавать, чтобы найти в логах
                        // конвертирование содержимого файла в строку. Это удобно, чтобы в логах выводилась стринга,
                        // а не внутренний объект сообщения
                        .convertBodyTo(String.class)
                        .log(">>>> ${body}") // логирование содержимого копируемого файла
                        // для логирования можно использовать модуль log, с помощью которого удобно достать
                        // метаинформацию по копируемым файлам и то же тело файла
                        .to("log:?showBody=true&showHeaders=true")
                        // реализуем сортировку, т.е. в зависимости вот body копируем в нужную папку
                        .choice()
                        .when(exchange -> ((String) exchange.getIn().getBody()).contains("=a"))
                            .to("file:{{toA}}")
                        .when(exchange -> ((String) exchange.getIn().getBody()).contains("=b"))
                            .to("file:{{toB}}")
                        .when(exchange -> ((String) exchange.getIn().getBody()).contains("=c"))
                            .to("file:{{toC}}")
                        .otherwise()
                        // есть поддержка мультикастинга, т.е. можно указать несколько папок для копирования
                            .to("file:{{out_folder}}", "file:{{err_folder}}")
                        // добавить ещё одну обработку файла, помимо указанной сортировки копируем файлы в root_dir
                        .end().to("file:{{root_folder}}");

            }
        });

        while (true) {
            camel.start();
        }
    }
}
