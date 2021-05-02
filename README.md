## Описание проекта
RESTful API для подачи обращений (претензий) и их обработки. Данное API вдохновлено [сайтом подачи обращений в ГУП "Мосгортранс"](https://mosgortrans.ru/passenger/obratnaja-svjaz/).

Данный проект реализован посредством фреймворка Spring Boot, в нем реализованы GET, POST, PUT, DELETE запросы для основных сущностей в формате JSON, 
реализовано разграничение прав пользователей с помощью Spring Sequrity и авторизация с помощью JSON WEB Token (JWT),
кэширование GET-запросов посредством инструмента кэширования Spring и управление кэшом посредством простого кэш-менеджера.

В качестве СУБД был выбран MySQL, для ORM был выбран Hibernate.

## Стек технолгий
+ Java
+ Maven
+ Spring (Boot, Securty)
+ Apache Tomcat
+ Hibernate
+ MySQL
## Диаграмма базы данных
![Диаграмма базы данных](https://github.com/martel42/claimer/blob/master/DBdiagram.png)

