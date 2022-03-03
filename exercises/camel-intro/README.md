# Введение в Camel

## Ссылки

* [Список всех компонентов (модулей)](https://camel.apache.org/components/3.15.x/index.html)
* [TIMER - генерация сообщений по расписанию](https://camel.apache.org/components/3.15.x/timer-component.html)
* [FILE - работа с файлами](https://camel.apache.org/components/3.15.x/file-component.html#_query_parameters)
* [Онлайн версия книги Camel in Action](https://livebook.manning.com/book/camel-in-action-second-edition/about-this-book/)
* [Примеры кода на GitHub](https://github.com/apache/camel-examples)
* [Текущий примир](https://github.com/apache/camel-examples/tree/main/examples/basic)
* [Конфигурация logback](https://logback.qos.ch/manual/configuration.html)


Apache Camel - продукт, который решает проблему взаимодействия между разными источниками и получателями данных.
Например, клиент делает запрос через web-интерфейс и его запрос попадает на backend, который может положить его в очередь,
после данные сходят на другой сервис, который возьмёт что-то из БД и положит в очередь, по пути может нужно отправить почту или
запостть что-то в слак или выложеть что-то на ftp. В итоге имеем кучу взаимодействий, при которых совершаются такие действия как:
парсинг данных, фильтрация, модификация. Camel как раз и нужен для таких типовых задач

Для конфигурирования Camel можно использовать Java DSL, XML

Компоненты - это модули, которые позволяют заинтегрироваться с внешнимио бъектами, которые находятся, например, за пределами нашего приложения.
Модуль может быть как источником так и приёмником информации 

## Зависимости
SLF4J или logback (используется только в связке с оберткой SLF4J) обязателен для работы camel.
Желательно определить минимальную его конфигурацию, иначе, из коробки, все в DEBUG

```sh
implementation 'org.apache.camel:camel-core:3.4.2'
implementation 'ch.qos.logback:logback-classic:1.0.13'
```

## src/main/java/exercise/App.java

