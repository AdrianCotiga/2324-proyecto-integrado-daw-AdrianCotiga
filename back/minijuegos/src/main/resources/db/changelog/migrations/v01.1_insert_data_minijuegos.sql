--liquibase formatted sql

--changeset liquibase:v01.1_insert_into_rol stripComments:true splitStatements:true
INSERT INTO rol (nombre) VALUES ('ADMIN');
INSERT INTO rol (nombre) VALUES ('PLAYER');

--changeset liquibase:v01.1_insert_into_minijuego stripComments:true splitStatements:true
INSERT INTO minijuego (nombre) VALUES ('Piedra, papel o tijera');
INSERT INTO minijuego (nombre) VALUES ('Adivina la IP');
INSERT INTO minijuego (nombre) VALUES ('Diaparar, recargar o esquivar');
INSERT INTO minijuego (nombre) VALUES ('Simón dice');
INSERT INTO minijuego (nombre) VALUES ('Frío o caliente');
INSERT INTO minijuego (nombre) VALUES ('Sumas y restas');