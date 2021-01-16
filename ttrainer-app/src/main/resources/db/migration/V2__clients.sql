-- Clients
create table client (
  id int GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),
  name varchar (255) not null,
  email varchar (255) not null,
  phone int not null,
  sex varchar (255) not null,
  height int,
  birth_date date not null,
  init_date date not null,
  end_date date,
  calendar_notes varchar (255),
  nutritional_notes varchar (255),
  training_notes varchar (255),
  app_user_name varchar (255),
  foreign key (app_user_name) references app_user(name) ON DELETE CASCADE ON UPDATE CASCADE,
  primary key (id)
);
-- Data
insert into capability values ('client_clients', '/client/clients/**');
insert into role_capability values ('entrenador', 'client_clients');
insert into role_write_capability values ('entrenador', 'client_clients');



