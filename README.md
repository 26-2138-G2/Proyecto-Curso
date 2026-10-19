# ResourceBook ---------------- Proyecto-Curso
Pequeño proyecto para el curso de Desarrollo de Aplicaciones con Hibernate y Spring.

ResourceBook


Sistema web de reservas y gestión de préstamos de material

Java

Spring Boot

PostgreSQL

Thymeleaf

REST API

Aplicación web para gestionar recursos, reservas, entregas y devoluciones de material en pequeñas organizaciones.

## Descripción

ResourceBook es una aplicación web desarrollada como proyecto personal de aprendizaje dentro de una especialización de Desarrollo de Aplicaciones Web (DAW).

Su objetivo es facilitar la gestión de material compartido en academias, centros educativos y pequeñas empresas, centralizando el catálogo de recursos, las reservas y el seguimiento de los préstamos.

La aplicación permitirá consultar los recursos disponibles, realizar reservas para un periodo determinado y registrar la entrega y devolución del material.

El proyecto se desarrollará utilizando Java y Spring Boot, con PostgreSQL como sistema de gestión de base de datos. Dispondrá de una interfaz web construida con Thymeleaf y una API REST propia para consultar y gestionar los datos mediante peticiones HTTP.

Además, se trabajará progresivamente con herramientas de automatización, contenedores y despliegue, siguiendo las tecnologías de la especialización.

## Objetivos del proyecto

* Desarrollar una aplicación web completa utilizando Java y Spring Boot.
* Diseñar una base de datos relacional y aplicar JPA e Hibernate para la persistencia.
* Implementar operaciones CRUD para gestionar los recursos.
* Desarrollar un sistema de reservas que compruebe la disponibilidad del material.
* Gestionar el ciclo de vida de los préstamos y las devoluciones.
* Crear una interfaz web con Thymeleaf.
* Exponer una API REST que devuelva respuestas JSON.
* Aplicar validaciones, gestión de errores y pruebas automatizadas.
* Familiarizarse con Git, GitHub, Jenkins, Docker y Kubernetes.

## Funcionalidades

### Gestión de recursos

* Registrar nuevos recursos.
* Consultar el catálogo de material.
* Consultar los detalles de un recurso.
* Editar la información de los recursos.
* Clasificar los recursos por categorías.
* Filtrar recursos por categoría y estado.
* Marcar recursos como disponibles, en mantenimiento o retirados.

### Gestión de reservas

* Crear reservas para un recurso y un periodo determinado.
* Consultar las reservas registradas.
* Consultar las reservas asociadas a un usuario.
* Cancelar reservas.
* Validar las fechas de inicio y fin.
* Comprobar que no existan reservas incompatibles para un mismo recurso.

### Gestión de préstamos

* Registrar la entrega de un recurso asociado a una reserva.
* Consultar los préstamos pendientes de devolución.
* Registrar la devolución del material.
* Consultar el historial de préstamos.
* Añadir observaciones relacionadas con la entrega o devolución.

### Panel de control

* Consultar el número total de recursos.
* Mostrar las reservas pendientes.
* Mostrar los préstamos que todavía no se han devuelto.
* Consultar los recursos que se encuentran en mantenimiento.

> El alcance inicial se limita a las funcionalidades esenciales. La autenticación, los roles, las notificaciones y otras características avanzadas se consideran posibles ampliaciones.

## Tecnologías utilizadas

| Tecnología | Finalidad |
| --- | --- |
| Java | Lenguaje principal de desarrollo. |
| Maven | Gestión de dependencias y construcción del proyecto. |
| Spring Boot | Desarrollo de la aplicación backend. |
| Spring Web | Controladores MVC y endpoints REST. |
| Spring Data JPA | Acceso y persistencia de datos. |
| Hibernate | Implementación del mapeo objeto-relacional. |
| Thymeleaf | Generación de las páginas HTML del servidor. |
| PostgreSQL | Sistema de gestión de base de datos. |
| Supabase | Servicio de alojamiento de PostgreSQL. |
| Git | Control de versiones. |
| GitHub | Repositorio remoto y documentación. |
| Jenkins | Automatización de compilación y pruebas. |
| Docker | Construcción y ejecución de contenedores. |
| Kubernetes | Despliegue y gestión básica de la aplicación. |

La implementación de cada tecnología se realizará progresivamente conforme avance el desarrollo y la formación.

## Modelo de datos

La primera versión contempla cinco entidades principales.

| Entidad | Descripción |
| --- | --- |
| `Usuario` | Persona que realiza las reservas. |
| `Categoria` | Clasificación de los recursos. |
| `Recurso` | Unidad física de material que puede reservarse. |
| `Reserva` | Solicitud de uso de un recurso durante un periodo. |
| `Prestamo` | Registro de la entrega y devolución de una reserva. |

### Relaciones entre entidades

* Una categoría puede contener muchos recursos.
* Un usuario puede realizar muchas reservas.
* Un recurso puede tener muchas reservas a lo largo del tiempo.
* Una reserva puede tener cero o un préstamo asociado.
* Un préstamo pertenece a una única reserva.

Cada unidad física de material tendrá un código identificativo único. Por ejemplo, varios portátiles se registrarán como recursos independientes, permitiendo conocer cuál se ha reservado y cuál se ha prestado.

## Arquitectura de la aplicación

El proyecto seguirá una arquitectura monolítica organizada por capas, con responsabilidades separadas.

text

    ResourceBook/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── .../
    │   │   │       ├── controller/
    │   │   │       ├── service/
    │   │   │       ├── repository/
    │   │   │       ├── entity/
    │   │   │       ├── dto/
    │   │   │       ├── exception/
    │   │   │       └── config/
    │   │   └── resources/
    │   │       ├── templates/
    │   │       ├── static/
    │   │       └── application.properties
    │   └── test/
    ├── Dockerfile
    ├── .gitignore
    ├── pom.xml
    └── README.md

La estructura es orientativa y podrá adaptarse durante el desarrollo.

Responsabilidades principales:

* `controller`: recibe las peticiones web y REST.
* `service`: contiene las reglas de negocio y validaciones.
* `repository`: accede a la base de datos mediante Spring Data JPA.
* `entity`: contiene las entidades persistentes.
* `dto`: define los datos de entrada y salida de la API.
* `exception`: centraliza el tratamiento de errores.
* `config`: agrupa la configuración específica de la aplicación.

La interfaz Thymeleaf y la API REST utilizarán la misma capa de servicios para evitar duplicar las reglas de negocio.

## API REST

La aplicación dispondrá de una API REST propia que permitirá gestionar los principales recursos mediante peticiones HTTP y respuestas JSON.

Los siguientes endpoints son los previstos para el diseño inicial y podrán ajustarse durante la implementación.

| Método | Endpoint | Descripción |
| --- | --- | --- |
| GET | `/api/recursos` | Obtener el listado de recursos. |
| GET | `/api/recursos/{id}` | Consultar un recurso por su identificador. |
| POST | `/api/recursos` | Crear un recurso. |
| GET | `/api/reservas` | Consultar las reservas. |
| POST | `/api/reservas` | Crear una reserva. |
| PATCH | `/api/reservas/{id}/cancelacion` | Cancelar una reserva. |
| POST | `/api/reservas/{id}/entrega` | Registrar la entrega del material. |
| POST | `/api/prestamos/{id}/devolucion` | Registrar la devolución. |

La API contemplará validaciones de entrada y respuestas HTTP adecuadas para operaciones correctas, datos inválidos y recursos inexistentes.

## Reglas de negocio principales

* La fecha de fin de una reserva debe ser posterior a la fecha de inicio.
* Un recurso retirado o en mantenimiento no podrá reservarse.
* Un recurso no podrá tener reservas activas que se solapen temporalmente.
* Una reserva cancelada no deberá bloquear la disponibilidad.
* Una entrega deberá estar asociada a una reserva válida.
* Una reserva no podrá generar varios préstamos.
* Un préstamo devuelto no podrá volver a registrarse como pendiente de devolución.
* Las operaciones que impliquen varios cambios relacionados deberán mantener la consistencia de los datos.

Estas reglas se implementarán en la capa de servicios y se verificarán mediante pruebas.

## Interfaz web

La aplicación incluirá inicialmente las siguientes páginas:

| Página | Funcionalidad |
| --- | --- |
| `/recursos` | Catálogo de recursos y filtros. |
| `/recursos/nuevo` | Formulario de creación. |
| `/recursos/{id}` | Detalle de un recurso. |
| `/reservas` | Listado de reservas. |
| `/reservas/nueva` | Formulario de reserva. |
| `/prestamos` | Listado de préstamos pendientes. |
| `/prestamos/{id}` | Detalle y gestión de la devolución. |

Las páginas se generarán en el servidor mediante Thymeleaf. Los formularios incluirán validaciones y mensajes para informar de los errores o del resultado de las operaciones.

## Base de datos y configuración

La aplicación utilizará PostgreSQL alojado en Supabase.

La configuración de conexión se realizará mediante variables de entorno para evitar almacenar credenciales en el código fuente.

Variables previstas:

text

    DB_URL
    DB_USERNAME
    DB_PASSWORD

Los valores concretos dependerán de la configuración del proyecto de Supabase y del método de conexión elegido.

Las credenciales no deberán incluirse en el repositorio ni en capturas de pantalla públicas. El archivo `application.properties` podrá utilizar referencias a dichas variables de entorno.

## Pruebas

Se realizarán pruebas progresivamente para comprobar el comportamiento de la aplicación.

Los principales casos que se deberán verificar son:

* Creación, consulta, modificación y retirada de recursos.
* Validación de fechas de reserva.
* Rechazo de reservas solapadas.
* Cancelación y actualización de la disponibilidad.
* Registro de entrega y devolución.
* Rechazo de operaciones sobre recursos inexistentes.
* Respuestas correctas de los endpoints REST.
* Persistencia de los cambios en PostgreSQL.

Se utilizarán las herramientas de pruebas del ecosistema Spring y Java que se incorporen al proyecto.

## Automatización y despliegue

Como parte de la especialización, se prevé incorporar las siguientes etapas:

1. Jenkins: automatizar la compilación y ejecución de pruebas.
2. Docker: empaquetar la aplicación en una imagen reproducible.
3. Kubernetes: preparar un despliegue básico y comprobar el estado de la aplicación.

El despliegue se abordará cuando las funcionalidades principales estén implementadas y probadas. La configuración final dependerá de las herramientas y el entorno disponibles durante la formación.

## Instalación y ejecución

Los pasos definitivos se documentarán una vez configurado el proyecto.

Requisitos previstos:

* JDK compatible con la versión de Spring Boot elegida.
* Maven, o utilizar el Maven Wrapper del proyecto.
* Una base de datos PostgreSQL accesible.
* IntelliJ IDEA u otro entorno de desarrollo Java.

Procedimiento general:

1. Clonar el repositorio.
2. Configurar las variables de entorno de la base de datos.
3. Ejecutar las tareas de Maven para compilar y probar el proyecto.
4. Iniciar la aplicación Spring Boot.
5. Acceder a la dirección local y comprobar las páginas web.

Los comandos concretos se añadirán después de crear y configurar el proyecto.

## Posibles ampliaciones

Una vez finalizada la versión inicial, se podrán valorar las siguientes mejoras:

* Autenticación y autorización mediante Spring Security.
* Roles diferenciados para administradores y usuarios.
* Búsqueda avanzada y paginación.
* Documentación interactiva de la API con OpenAPI.
* Historial de cambios y auditoría.
* Notificaciones de vencimiento de préstamos.
* Pruebas de integración más completas.
* Automatización avanzada del pipeline de despliegue.

Estas mejoras no forman parte del alcance obligatorio de la primera versión.

## Estado del proyecto

Fase actual: definición y planificación.

El proyecto se desarrollará de forma incremental, priorizando primero el modelo de datos, el catálogo de recursos y la gestión de reservas. Después se incorporarán los préstamos, la API REST, las pruebas y las herramientas de despliegue.
