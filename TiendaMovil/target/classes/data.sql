insert into USUARIO (id,nombre, apellidos, correo, contrasenya, nom_usuario, admin) values (nextval('hibernate_sequence'),'admin', 'admin', 'admin@admin.com', '$2y$12$KejT/qL4i4Js/n5RfRpzNuFD82bUtJkVRnlCAAqev1V2EfZYJVCEe', 'admin', true);

insert into USUARIO (id,nombre, apellidos, correo, contrasenya, nom_usuario, admin) values (nextval('hibernate_sequence'),'usuario', 'usuario', 'usuario@usuario.com', '$2y$12$IDXjBbq/KBwa7UG3z2sba.CqfHKqLPr.eTmTfggM4KRhr3MYKfOZ6', 'usuario', false);