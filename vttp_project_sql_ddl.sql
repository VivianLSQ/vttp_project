-- create database vttp_project; 
use vttp_project; 
/*
create table tasks(
	task_id	  int not null auto_increment,
    content	  varchar(128) not null,
    date_added date,
    due_date   date,
    notes     text,
    constraint pk_tasks_id primary key(task_id)
); 



create table taskDetails(
	id	     int not null auto_increment,
    is_completed	 boolean not null,
    category     char(128) not null,
    priority 	 char(64) not null,
    task_id		int not null,
    constraint pk_task_details_id primary key (id),
    constraint fk_task_details_order foreign key (task_id) references tasks(task_id) ON DELETE CASCADE
); 
 */
 
create table geoLocation(
	location_id int not null auto_increment,
    location_name char(64),
    latitude decimal,
    longitide decimal,
    radius varchar(64),
    primary key (location_id)
);

create table users
(
    id           int auto_increment,
    email        varchar(64) not null unique,
    username 	 varchar(32) not null unique,
    password     varchar(64) not null,
	homeAddress   varchar(64), 
    workAddress   varchar(64), 
    primary key (id)
);




