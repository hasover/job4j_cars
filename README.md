# job4j_cars
[![Build Status](https://app.travis-ci.com/hasover/job4j_cars.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_cars)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Контакты](#контакты)

## Описание
Основная страница содержит таблицу со всеми объявлениями машин на продажу.Авторизованные пользователи могут создавать новые объявления и удалять созданные.

## Технологии
* PostgreSQL
* HQL
* Hibernate
* Servlet
* HTML, BOOTSTRAP, JS, Ajax
* Apache Tomcat
* Travis CI

## Функционал

### 1. Главная страница.
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/1.PNG)
### 2. Авторизация.
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/2.PNG)
### 3. Регистрация.
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/3.PNG)
### 4. Добавление нового объявления.
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/4.PNG)
### 5. Главная страница со стороны разных пользователей.
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/5.PNG)
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/6.PNG)
![alt text](https://github.com/hasover/job4j_cars/blob/master/images/7.PNG)

## Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
    - Tomcat
- Укажите настройки для подключения к БД в файле `src/main/resources/hibernate.cfg.xml`
- Выполните скрипт `db/schema.sql`
- Выполните команду `mvn package`
- Файл `target/job4j_cars.war` скопируйте в webapp tomcat

Приложение будет доступно по адресу: http://localhost:8080/job4j_cars

## Контакты
telegram: @hasover
