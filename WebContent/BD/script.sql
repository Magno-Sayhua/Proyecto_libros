 create database librosAlmacen;
 use librosAlmacen;
 
 
 create table almacen_libro (
    cod_libro int not null primary key auto_increment,
    titulo varchar(40),
    autor varchar(40),
    fecha_ingreso date,
    stock int,
    categoria varchar(40)
);

insert into almacen_libro values(1, 'Tradiciones Peruanas', 'Ricardo Palma', '2026-01-10', 15, 'Relato Histórico');
insert into almacen_libro values(2, 'Los Ríos Profundos', 'José María Arguedas', '2026-02-15', 10, 'Literatura Indigenista');
insert into almacen_libro values(3, 'La Ciudad y los Perros', 'Mario Vargas Llosa', '2026-03-20', 8, 'Novela Contemporánea');
insert into almacen_libro values(4, 'Enredada', 'Paola Arenas', '2026-04-01', 12, 'Literatura Juvenil');

select * from almacen_libro;

create table prestamo_libro (
    cod_prestamo int not null primary key auto_increment,
    nom_usuario varchar(30),
    ape_usuario varchar(30),
    fecha_prestamo date,
    fecha_devolucion date,
    estado varchar(30),
    cod_libro int references almacen_libro
);

insert into prestamo_libro values(null, 'Carlos Alejandro', 'Magno Sayhua', '2026-05-10', '2026-05-17', 'Activo', 1);
insert into prestamo_libro values(null,'Dana Deniss', 'Melendez Dominguez', '2026-05-08', '2026-05-15', 'Activo', 3);
insert into prestamo_libro values(null,'Claudio Estefano', 'Rojas Guillén', '2026-05-01', '2026-05-08', 'Devuelto', 2);
insert into prestamo_libro values(null,'Dara Aylin', 'Beteta Culantres', '2026-03-27', '2026-04-02', 'Devuelto', 4);


select * from prestamo_libro;

SELECT p.cod_prestamo, p.cod_libro, a.titulo, p.nom_usuario, p.ape_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado
FROM prestamo_libro p 
JOIN almacen_libro a 
ON p.cod_libro = a.cod_libro;

SELECT p.cod_prestamo, p.cod_libro, a.titulo, p.nom_usuario, p.ape_usuario, p.fecha_prestamo, p.fecha_devolucion, p.estado
FROM prestamo_libro p 
JOIN almacen_libro a 
ON p.cod_libro = a.cod_libro
WHERE p.cod_prestamo = 1;

SELECT titulo 
FROM almacen_libro 
WHERE cod_libro = 2;



