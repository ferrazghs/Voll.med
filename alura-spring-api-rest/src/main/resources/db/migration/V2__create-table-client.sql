create table client (

    id bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique key,
    telephone varchar(100) not null,
    cpf varchar(100) not null,
    address varchar(100) not null,
    number int,
    district varchar(100) not null,
    city varchar(100) not null,
    uf varchar(2) not null,
    cep varchar(100) not null,
    complement varchar(100)
)