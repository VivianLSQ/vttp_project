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
*/


create table taskDetails(
	id	     int not null auto_increment,
    is_completed	 boolean not null,
    category     char(128) not null,
    priority 	 char(64) not null,
    task_id		int not null,
    constraint pk_task_details_id primary key (id),
    constraint fk_task_details_order foreign key (task_id) references tasks(task_id) ON DELETE CASCADE
); 

