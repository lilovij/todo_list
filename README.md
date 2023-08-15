# todo_list

Выполнение тестового задания на стажировку. 

ТЗ: создать TODO REST API с возможностью вывода задач на день, неделю и месяц.

В пакете src/test/java/com.ilya.todo_list написан простой тест контроллера (TaskTest), в котором тестируется добавление, удаление,
изменение задач, а также вывод задач на день, неделю и месяц.

Также для тестирования использовал Postman, в котором проверял работоспособность всех методов REST API.

В качестве базы данных использовал MySQL Workbench. SQL cкрипт для создания таблицы:

CREATE TABLE tasks (
id int NOT NULL AUTO_INCREMENT,
isdone bool,
name varchar(50),
description text,
created timestamp,
deadline timestamp,
PRIMARY KEY (id)
);
