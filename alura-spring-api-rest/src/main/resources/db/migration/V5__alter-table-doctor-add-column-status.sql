alter table doctor
add column status tinyint;

update doctor set status = 1;
