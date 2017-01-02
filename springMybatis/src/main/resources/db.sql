create table person(
	pid int primary key auto_increment,
	pname varchar(100)
);

insert into person(pname) values('a');
insert into person(pname) values('b');

select * from person;
commit;
