insert into USUARIO (id,nombre, apellidos, correo, password, username, is_admin) values (nextval('hibernate_sequence'),'admin', 'admin', 'admin@admin.com', '$2a$10$sbNAUG/zX5oRekmEWKP0A.1rYn/86YiOnxjaje2vVccxNDm98tBP6', 'admin', true);
insert into USUARIO (id,nombre, apellidos, correo, password, username, is_admin) values (nextval('hibernate_sequence'),'usuario', 'usuario', 'usuario@usuario.com', '$2a$10$8pHfjdq8zHKT6e8TqHM/iOSV2plG1cM/3fsOqh5MGqUq4NNCRL.AW', 'usuario', false);

insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'iPhone XS Max', 'Rojo', 128, 4, '2018-11-26', 1500, 10, 'movil1.png');
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'Samsung Galaxy s10+', 'Plata', 128, 8, '2019-04-26', 899.95, 10, 'movil2.jpg');
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'Huawei P30 Pro', 'Aurora', 128, 8, '2019-05-05', 799, 10, 'movil3.jpg');
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'Honor view 20', 'Azul safiro', 256, 8, '2019-04-10', 549, 10, 'movil4.jpg');
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'Xiaomi mi 9', 'Negro piano', 128, 6, '2018-11-18', 499, 10, 'movil5.jpeg');
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad, file_url) values (nextval('hibernate_sequence'), 'LG V40 ThinkQ', 'Naranja metalizado', 128, 6, '2018-10-27', 899, 10, 'movil6.jpg');
