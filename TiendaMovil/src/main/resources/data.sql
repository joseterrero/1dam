insert into USUARIO (id,nombre, apellidos, correo, contrasenya, nom_usuario, admin) values (nextval('hibernate_sequence'),'admin', 'admin', 'admin@admin.com', '$2y$12$KejT/qL4i4Js/n5RfRpzNuFD82bUtJkVRnlCAAqev1V2EfZYJVCEe', 'admin', true);
insert into USUARIO (id,nombre, apellidos, correo, contrasenya, nom_usuario, admin) values (nextval('hibernate_sequence'),'usuario', 'usuario', 'usuario@usuario.com', '$2y$12$IDXjBbq/KBwa7UG3z2sba.CqfHKqLPr.eTmTfggM4KRhr3MYKfOZ6', 'usuario', false);

insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'iPhone XS Max', 'Rojo', 128, 4, '2018-11-26', 1500, 10);
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'Samsung Galaxy s10+', 'Plata', 128, 8, '2019-04-26', 899.95, 10);
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'Huawei P30 Pro', 'Aurora', 128, 8, '2019-05-05', 799, 10);
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'Honor view 20', 'Azul safiro', 256, 8, '2019-04-10', 549, 10);
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'Xiaomi mi 9', 'Negro piano', 128, 6, '2018-11-18', 499, 10);
insert into PRODUCTO (id, modelo, color, capacidad, ram, fecha, precio, cantidad) values (nextval('hibernate_sequence'), 'LG V40 ThinkQ', 'Naranja metalizado', 128, 6, '2018-10-27', 899, 10);