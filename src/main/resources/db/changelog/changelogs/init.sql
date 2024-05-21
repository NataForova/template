---TODO create parameter for service name
create table if not exists template (
    id integer not null primary key,
    text varchar(255)
);
insert into template (id, text) VALUES (1, 'test')