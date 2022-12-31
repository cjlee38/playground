drop table if exists domain_entity;

create table domain_entity
(
    id        bigint,
    item_name varchar(255),
    quantity  bigint,
    primary key (id)
);


insert into domain_entity (id, item_name, quantity)
VALUES (1, '항목', 100);
