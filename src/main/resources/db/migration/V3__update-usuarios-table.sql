-- V3__update-usuarios-table.sql
ALTER TABLE usuarios
CHANGE COLUMN logIn login VARCHAR(100) NOT NULL,
CHANGE COLUMN clave clave VARCHAR(300) NOT NULL;
