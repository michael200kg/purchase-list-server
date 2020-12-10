CREATE TABLE db.user
(
    username varchar(100) NOT NULL,
    fio      varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    api_key  varchar(255) DEFAULT NULL,
    PRIMARY KEY (username)
);

insert into db.user
values ('michael', 'Михаил Вершков', '$2a$10$5SoJ8kJxh2P/NRVaLrWZyelNsy4oiN.MUdJ.SEreoSicwNpBzJKnS', null);

insert into db.user
values ('koshka', 'Евгения Корсакова', '$2a$10$f1uXp9qqyH6TqqG1jHdc3OR2ffwQD8AQjklY5.1XURHPRW/6N7Q..', null);

update db.purchase set username = 'michael';

commit;

