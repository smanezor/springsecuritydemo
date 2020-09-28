create SCHEMA IF NOT EXISTS "test";
SET search_path TO "test";

create TABLE users
(
    id bigint not null,
    email varchar(255),
    first_name varchar(50) not null,
    last_name varchar(100) not null,
    password varchar(255) not null,
    role varchar(20) default 'USER' not null,
    status varchar(20) default 'ACTIVE' not null,
CONSTRAINT "pk_test.users" PRIMARY KEY (id)
);