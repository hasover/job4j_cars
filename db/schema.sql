create table users (
    id serial primary key,
    name varchar(250),
    login varchar(250) unique ,
    password varchar(250)
);
create table car_brands(
    id serial primary key,
    name varchar(250)
);
create table car_bodies (
    id serial primary key,
    name varchar(250)
);
create table ads (
    id serial primary key,
    description varchar(1000),
    carbrand_id int references car_brands(id),
    carbody_id int references  car_bodies(id),
    photo varchar(100),
    is_sold boolean,
    created timestamp,
    user_id int not null references users(id)
);

