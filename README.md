# Gestión de Películas con Microservicios Avanzado

Este proyecto es la evolución de una aplicación web para la gestión de películas, desarrollada inicialmente en el curso de Frameworks y Backend. Se ha ampliado y mejorado con nuevas funcionalidades y la integración de microservicios, reforzando el aprendizaje sobre arquitecturas distribuidas y comunicación entre servicios, similar a Filmaffinity.

## Objetivo del Proyecto

Ampliar la aplicación inicial para incluir:

* Gestión de usuarios y seguridad (registro, autenticación con JWT, administración, roles ADMIN y USER).
* Gestión de críticas de películas por usuarios registrados (valoraciones y comentarios).
* Implementación de una arquitectura de microservicios con Eureka y API Gateway.

## Características de la Aplicación

* **Gestión de Películas:** Alta, baja, modificación, listado, relación de actores.
* **Gestión de Actores:** Alta, baja, modificación, listado, relación de películas.
* **Búsqueda de Películas:** Título, género, actor, país, dirección, año, duración.
* **Búsqueda de Actores:** Nombre, país, películas.
* **Gestión de Usuarios:** Registro, autenticación (JWT), alta, baja, modificación, listado, seguridad basada en roles.
* **Búsqueda de Usuarios:** Nombre de usuario, correo electrónico.
* **Gestión de Críticas:** Creación, visualización (en detalle de películas), solo administradores pueden dar de baja.
* **Búsqueda de Críticas:** Por películas, por usuarios.
* **Nota Media:** La nota media de las criticas sale en el detalle de las peliculas.

## Tecnologías Utilizadas

* Spring Boot
* Microservicios
* JPA (Hibernate)
* MySQL
* Servicios REST
* MVC
* Thymeleaf
* Maven
* Eureka Server
* Spring Cloud Gateway
* Spring Security
* JWT

## Descripción Técnica

### Microservicios de Lógica de Negocio

* **Microservicio de Películas y Actores (Backend):**
    * Operaciones CRUD.
    * APIs RESTful.
    * Persistencia con JPA (MySQL).
    * Modelos: Película, Actor (relación ManyToMany).
    * Repositorios: Spring Data JPA, Interfaces DAO.
    * Servicios: Lógica de negocio.
    * Controladores: Endpoints RESTful.
    * Pruebas: Pruebas unitarias para controladores y servicios.
    * Postman: pruebas realizadas con postman, archivos en la carpeta Postman.
    * Dependencias principales: `spring-boot-starter-data-jpa`, `spring-boot-starter-web`, `mysql-connector-java`.
* **Microservicio de Usuarios y Críticas (Seguridad):**
    * Gestión de usuarios y críticas.
    * Seguridad con Spring Security y JWT.
    * Persistencia con JPA (MySQL).
    * Modelos: User, Authority, Criticas.
    * Controladores: Endpoints para login, registro, gestión de críticas y usuarios.
    * Postman: pruebas realizadas con postman, archivos en la carpeta Postman.
    * Dependencias principales: `spring-boot-starter-security`, `spring-boot-starter-data-jpa`, `spring-boot-starter-web`, `mysql-connector-java`, `jjwt`.

### Microservicio de Frontend

* Consumo de APIs RESTful.
* Thymeleaf.
* MVC.
* RestTemplate.
* Modelos (incluyendo Enum de países), Controladores (Películas, Actores, Home), Vistas (Paginator, Layout), Servicios.
* Servicio de subida de imágenes.
* Dependencias principales: `spring-boot-starter-thymeleaf`, `spring-boot-starter-web`, `spring-web`.

### Infraestructura y Comunicación

* **Eureka Server:**
    * Descubrimiento de microservicios.
    * Dependencias principales: `spring-cloud-starter-netflix-eureka-server`.
* **Spring Cloud Gateway:**
    * Puerta de enlace.
    * Dependencias principales: `spring-cloud-starter-gateway`, `spring-cloud-starter-netflix-eureka-client`.

## Modelo de Datos

* **Película:** idPeliculas, Titulo, Annio, Direccion, Genero, Sinopsis, Duracion, ImagenPortada, Pais.
* **Actor:** idActor, Nombre, FechaNacimiento, Pais.
* **Reparto:** idPelicula, idActor.
* **Users:** id, username, email, password, enabled, created\_at, authority\_id.
* **Authorities:** id, authority.
* **Criticas:** id, peliculaid, userId, valoracion, nota, fecha.

## Instrucciones de Ejecución

1.  Clonar el repositorio.
2.  Crear las bases de datos MySQL con los scripts de la carpeta `database`.
3.  Configurar las conexiones a las bases de datos en `application.properties` de cada microservicio.
4.  Ejecutar Eureka Server: `cd PortalPeliculas_EurekaServer && mvn spring-boot:run`.
5.  Ejecutar Spring Cloud Gateway: `cd PortalPeliculas_Gateway && mvn spring-boot:run`.
6.  Ejecutar el microservicio de peliculas: `cd PortalPeliculas_Microservicio_ActorYPelicula && mvn spring-boot:run`.
7.  Ejecutar el microservicio de usuarios: `cd PortalPeliculas_Usuarios_Seguridad && mvn spring-boot:run`.
8.  Ejecutar Frontend: `cd frontend && mvn spring-boot:run`.
9.  Acceder en `http://localhost:9010`.
