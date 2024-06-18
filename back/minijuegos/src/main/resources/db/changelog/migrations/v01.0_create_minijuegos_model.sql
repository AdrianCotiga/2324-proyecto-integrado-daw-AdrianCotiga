--liquibase formatted sql

--changeset liquibase:v01.0_create_table_rol stripComments:true splitStatements:true
CREATE TABLE "rol"(
	"id_rol"	BIGSERIAL	NOT NULL,
	"nombre"	VARCHAR(30)	NOT NULL
);

--changeset liquibase:v01.0_create_constraint_pk_id_rol stripComments:true splitStatements:true
ALTER TABLE "rol"
ADD CONSTRAINT "pk_id_rol"
PRIMARY KEY ("id_rol");	

--changeset liquibase:v01.0_create_table_usuario stripComments:true splitStatements:true
CREATE TABLE "usuario"(
	"id_usuario"	BIGSERIAL	NOT NULL,
	"fk_id_rol"		BIGINT		NOT NULL,
	"nombre"		VARCHAR(30)	NOT NULL,
	"contrasenya"	VARCHAR(60)	NOT NULL
);

--changeset liquibase:v01.0_create_constraint_pk_id_usuario stripComments:true splitStatements:true
ALTER TABLE "usuario"
ADD CONSTRAINT "pk_id_usuario"
PRIMARY KEY ("id_usuario");

--changeset liquibase:v01.0_create_constraint_fk_rol_usuario stripComments:true splitStatements:true
ALTER TABLE "usuario"
ADD CONSTRAINT "fk_rol_usuario"
FOREIGN KEY ("fk_id_rol")
REFERENCES "rol" ("id_rol");

--changeset liquibase:v01.0_create_table_perfil stripComments:true splitStatements:true
CREATE TABLE "perfil"(
	"id_perfil"			BIGSERIAL	NOT NULL,
	"fk_id_usuario"		INT			NOT NULL,
	"fecha_creacion"	DATE		NOT NULL,
	"nivel"				INT			DEFAULT 0	NOT NULL,
	"habilidad"			INT			DEFAULT 0	NOT NULL,
	"partidas"			INT			DEFAULT 0	NOT NULL,
	"atencion"			INT			DEFAULT 0,
	"logica"			INT			DEFAULT 0,
	"matematicas"		INT			DEFAULT 0,
	"memoria"			INT			DEFAULT 0,
	"visual"			INT			DEFAULT 0,
	"suerte"			INT			DEFAULT 0
);

--changeset liquibase:v01.0_create_constraint_pk_id_perfil stripComments:true splitStatements:true
ALTER TABLE "perfil"
ADD CONSTRAINT "pk_id_perfil"
PRIMARY KEY ("id_perfil");

--changeset liquibase:v01.0_create_constraint_fk_usuario_perfil stripComments:true splitStatements:true
ALTER TABLE "perfil"
ADD CONSTRAINT "fk_usuario_perfil"
FOREIGN KEY ("fk_id_usuario")
REFERENCES "usuario" ("id_usuario");

--changeset liquibase:v01.0_create_table_minijuego stripComments:true splitStatements:true
CREATE TABLE "minijuego"(
	"id_minijuego"	BIGSERIAL	NOT NULL,
	"nombre"		VARCHAR(50)	NOT NULL
);

--changeset liquibase:v01.0_create_constraint_pk_id_minijuego stripComments:true splitStatements:true
ALTER TABLE "minijuego"
ADD CONSTRAINT "pk_id_minijuego"
PRIMARY KEY ("id_minijuego");

--changeset liquibase:v01.0_create_table_partida stripComments:true splitStatements:true
CREATE TABLE "partida"(
	"id_partida"		BIGSERIAL	NOT NULL,
	"fk_id_usuario"		BIGINT		NOT NULL,
	"fk_id_minijuego"	BIGINT		NOT NULL,
	"fecha_creacion"	DATE		NOT NULL,
	"concentracion"		INT,
	"resultado"			INT
);

--changeset liquibase:v01.0_create_constraint_pk_id_partida stripComments:true splitStatements:true
ALTER TABLE "partida"
ADD CONSTRAINT "pk_id_partida"
PRIMARY KEY ("id_partida");

--changeset liquibase:v01.0_create_constraint_fk_usuario_partida stripComments:true splitStatements:true
ALTER TABLE "partida"
ADD CONSTRAINT "fk_usuario_partida"
FOREIGN KEY ("fk_id_usuario")
REFERENCES "usuario" ("id_usuario");

--changeset liquibase:v01.0_create_constraint_fk_minijuego_partida stripComments:true splitStatements:true
ALTER TABLE "partida"
ADD CONSTRAINT "fk_minijuego_partida"
FOREIGN KEY ("fk_id_minijuego")
REFERENCES "minijuego" ("id_minijuego");
