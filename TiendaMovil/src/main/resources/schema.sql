    
    drop table linea_pedido if exists
    
    drop table pedido if exists
    
    drop table producto if exists
    
    drop table usuario if exists
    
    drop sequence if exists hibernate_sequence
	
	create sequence hibernate_sequence start with 1 increment by 1
    
    create table linea_pedido (id bigint not null, cantidad bigint not null, pedido_id bigint, producto_id bigint, primary key (id))
    
    create table pedido (id bigint not null, fecha date, usuario_id bigint, primary key (id))
    
    create table producto ( id bigint not null, cantidad bigint not null, capacidad bigint not null, color varchar(255), fecha date, file_url varchar(255), modelo varchar(255), precio double not null, ram bigint not null, usuario_id bigint, primary key (id))
    
    create table usuario (id bigint not null, apellidos varchar(255), correo varchar(255), is_admin boolean not null, nombre varchar(255), password varchar(255), username varchar(255), primary key (id))
    
    alter table usuario add constraint UK_2mlfr087gb1ce55f2j87o74t unique (correo)
    
    alter table linea_pedido add constraint FKoi533vfp5jf0jgf9dws0s0pw4 foreign key (pedido_id) references pedido
    
    alter table linea_pedido add constraint FKhhtgctnq5gn29qdye9todv6ku foreign key (producto_id) references producto
    
    alter table pedido add constraint FK6uxomgomm93vg965o8brugt00 foreign key (usuario_id) references usuario
    
    alter table producto add constraint FK4f8g2yvj0uj7hqxlauy8p8k39 foreign key (usuario_id) references usuario