# unexus-servicio

Bienvenido al proyecto unexus-servicio. Este es un proyecto que apoya al crecimiento de pequeñas y medianas empresas en Ecuador, brindando una solución de facturación electrónica y contabilidad. Como parte de la visión de Alquimiasoft, se busca expandir las soluciones tecnológicas empresariales a 1M de negocios en Latinoamérica.

Este proyecto se realiza como parte de un ejercicio técnico para demostrar habilidades de programación en Java, Spring Boot, y PostgreSQL.

## Prerrequisitos

- Java 8 o superior
- Maven
- Sistema de gestión de bases de datos PostgreSQL

## Configuración e instalación

1. Clona el repositorio a tu máquina local usando `git clone https://github.com/tonyveas/unexus-servicio.git`.
2. Navega hasta el directorio del proyecto con `cd unexus-servicio`.
3. Ejecuta `mvn clean install` para construir el proyecto e instalar las dependencias.
4. Crea una base de datos en PostgreSQL y asegúrate de que esté en ejecución.
5. Actualiza `application.properties` con las credenciales de tu base de datos.

Aquí tienes un ejemplo de configuración de la base de datos:

```properties
# Propiedades para configurar la base de datos
app.datasource.username=postgres
app.datasource.url=jdbc:postgresql://localhost:5432/unexus-database
app.datasource.password=root
app.datasource.driver-class-name=org.postgresql.Driver

## Uso

Para iniciar el servicio, ejecuta `mvn spring-boot:run` en la raíz del proyecto.

El servicio estará disponible en `http://localhost:8080`.

## APIs y funcionalidades

Este servicio proporciona un conjunto de API REST para gestionar los datos de los clientes y sus direcciones. Las funcionalidades incluyen:

- Buscar y obtener un listado de clientes.
- Crear un nuevo cliente con la dirección matriz.
- Editar los datos de un cliente.
- Eliminar un cliente.
- Registrar una nueva dirección para un cliente.
- Listar las direcciones adicionales de un cliente.

Consulte la documentación de la API para obtener más detalles.

## Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un "Fork" del proyecto.
2. Crea una nueva rama (`git checkout -b feature/fooBar`).
3. Realiza los cambios en el código.
4. Haz commit de tus cambios (`git commit -am 'Add some fooBar'`).
5. Haz push a la rama (`git push origin feature/fooBar`).
6. Crea una nueva Pull Request.

## Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.
```
