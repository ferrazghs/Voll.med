create table doctor (

    id bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique key,
    crm varchar(6) not null unique key,
    speciality varchar(50) not null,
    address varchar(100) not null,
    number int,
    district varchar(100) not null,
    city varchar(100) not null,
    uf varchar(2) not null,
    cep varchar(100) not null,
    complement varchar(100)
)