# Sistema Mi Negocio

Bienvenido al proyecto unexus-servicio. Este es un proyecto que apoya al crecimiento de pequeñas y medianas empresas en Ecuador, brindando una solución de facturación electrónica y contabilidad. Como parte de la visión de Alquimiasoft, se busca expandir las soluciones tecnológicas empresariales a 1M de negocios en Latinoamérica.

Este proyecto se realiza como parte de un ejercicio técnico para demostrar habilidades de programación en Java, Spring Boot, y PostgreSQL.

# Prerrequisitos

- Java 8 o superior
- Maven
- Sistema de gestión de bases de datos PostgreSQL

# Configuración e instalación

## Creación de base de datos (PostgreSQL)

Antes de clonar y ejecutar nuestra aplicación de spring boot necesitamos crear una base en PostgreSQL

1. Descargar PostgreSQL si no lo tiene, de acuerdo a su sistema operativo. (https://postgresql.org/download)
2. Configurar las credenciales de acceso para su base de datos. Para propósitos de ejercicio se uso (Pero puede configurarlo a su gusto):

```Credenciales
# Credenciales para acceder a la base de datos
username=postgres
password=root
```

3. Crear la base datos. Para propósitos de ejercicio se uso se usó el nombre:

```Base
# Nombre de base de datos en PostgreSQL
unexus-database
```

## Clonación y configuración de proyecto de Spring Boot (Usando Eclipse)

1. Abre Eclipse IDE.
2. Ve a `File > Import`.
3. En la ventana de importación, expande la carpeta `Maven` y selecciona `Existing Maven Projects`.
4. Haz clic en `Next`.
5. En la siguiente ventana, haz clic en `Browse` y navega hasta el directorio del repositorio clonado `unexus-servicio`.
6. Haz clic en `Finish`. El proyecto se importará en Eclipse.
7. Actualiza `application.properties` con las credenciales y las configuraciones de tu base de datos.

Aquí hay un ejemplo de configuración de la base de datos:

```properties
# Propiedades para configurar la base de datos
app.datasource.username=postgres
app.datasource.url=jdbc:postgresql://localhost:5432/unexus-database
app.datasource.password=root
app.datasource.driver-class-name=org.postgresql.Driver
```

8. Damos clic derecho en la carpeta de nuestro proyecto ec-prueba-unexus-servicio-tony-veas, Run As, escogemos Maven clean y esperamos que termine.
9. Damos nuevamente clic derecho en la carpeta ec-prueba-unexus-servicio-tony-veas, Run As, escogemos Maven install y esperamos que termine.
10. Dentro de nuestra carpeta/paquete ec-prueba-unexus-servicio-tony-veas, buscamos la clase EcPruebaUnexusServicioTonyVeasApplication.java, damos clic derecho en la clase, seleccionamos Run As y finalmente Java Application. Esperamos que la aplicación inicie.

# Uso

Para iniciar el servicio, ejecuta `mvn spring-boot:run` en la raíz del proyecto.

El servicio estará disponible en `http://localhost:8080`.

# APIs y funcionalidades

Este servicio proporciona un conjunto de API REST para gestionar los datos de los clientes y sus direcciones. Las funcionalidades incluyen:

- Buscar y obtener un listado de clientes.
- Crear un nuevo cliente con la dirección matriz.
- Editar los datos de un cliente.
- Eliminar un cliente.
- Registrar una nueva dirección para un cliente.
- Listar las direcciones adicionales de un cliente.

Consulte la documentación de la API para obtener más detalles.

# Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un "Fork" del proyecto.
2. Crea una nueva rama (`git checkout -b feature/fooBar`).
3. Realiza los cambios en el código.
4. Haz commit de tus cambios (`git commit -am 'Add some fooBar'`).
5. Haz push a la rama (`git push origin feature/fooBar`).
6. Crea una nueva Pull Request.

# Licencia

Este proyecto está licenciado bajo los términos de la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.
