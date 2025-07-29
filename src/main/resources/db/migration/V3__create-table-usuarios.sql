create table usuarios(
   id bigint not null auto_increment,
   nombre varchar(255) not null,
   correo_electronico varchar(100) not null,
   contrasena varchar(255) not null,
   perfiles_id bigint not null,

   primary key(id),

   constraint fk_usuarios_perfiles_id foreign key(perfiles_id) references perfiles(id)
);