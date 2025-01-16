Foro Hub: API REST para un foro de discusión


Descripción

Este proyecto es una entrega del desafío de backend dictado por Oracle en colaboración con Alura LATAM. El objetivo principal es 
desarrollar una API REST funcional para un foro de discusión, implementando funcionalidades CRUD para tópicos y utilizando tecnologías como Spring Boot y JWT para autenticación
y autorización.

Tecnologías utilizadas

Spring Boot: Framework para desarrollo de aplicaciones Java.
Java: Lenguaje de programación principal.
Base de datos: MySQL
Pruebas de la API: Insomnia


Estructura del proyecto

src/main/java: Contiene el código fuente de la aplicación.
src/test/java: Contiene las pruebas unitarias.
resources: Contiene archivos de configuración, como el archivo application.properties.


Instalación

Clonar el repositorio: git clone https://github.com/tu-usuario/foro-hub.git

Configurar la base de datos: Crea una base de datos con el nombre y las credenciales especificados en el archivo application.properties.

Instalar dependencias: ./gradlew build

Ejecutar la aplicación: ./gradlew bootRun


Uso

La API expone los siguientes endpoints:

POST /topicos: Crear un nuevo tópico.
GET /topicos: Obtener una lista de todos los tópicos.
GET /topicos/{id}: Obtener un tópico específico por su ID.
PUT /topicos/{id}: Actualizar un tópico existente.
DELETE /topicos/{id}: Eliminar un tópico.


Consideraciones de Seguridad

Encriptación de contraseñas: Se utiliza bcrypt para almacenar las contraseñas de forma segura.
JWT: Los tokens JWT se utilizan para autenticar a los usuarios y proteger las rutas de la API.
CORS: Se configura CORS para permitir solicitudes desde orígenes permitidos.

Contribuciones


Las contribuciones son bienvenidas. Por favor, abre un issue para discutir nuevas características o mejoras.


