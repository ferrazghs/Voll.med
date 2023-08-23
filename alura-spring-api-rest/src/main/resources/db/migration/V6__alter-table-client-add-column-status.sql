alter table client
add column status tinyint;

update client set status = 1;
