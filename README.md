# Sistema Mi Negocio

Bienvenido al proyecto unexus-servicio. Este es un proyecto que apoya al crecimiento de pequeñas y medianas empresas en Ecuador, brindando una solución de facturación electrónica y contabilidad. Como parte de la visión de Alquimiasoft, se busca expandir las soluciones tecnológicas empresariales a 1M de negocios en Latinoamérica.

Este proyecto se realiza como parte de un ejercicio técnico para demostrar habilidades de programación en Java, Spring Boot, y PostgreSQL.

# Prerrequisitos

- Java 8 o superior
- Maven
- Sistema de gestión de bases de datos PostgreSQL
- Git

# Configuración y ejecución de la aplicación

Puede realizar la configuración y ejecución de la aplicación siguiendo los pasos que se muestran más abajo, o para una mejor comprensión le invito a ver el siguiente video:

[![Demo Video](http://img.youtube.com/vi/P0DIXstSm8c/0.jpg)](https://youtu.be/P0DIXstSm8c "Configuración y ejecución de la aplicación")

## Creación de base de datos (PostgreSQL)

Antes de clonar y ejecutar nuestra aplicación de spring boot necesitamos crear una base en PostgreSQL

1. Descargar PostgreSQL si no lo tiene, de acuerdo a su sistema operativo. (https://postgresql.org/download)
2. Configurar las credenciales de acceso para su base de datos. Para propósitos de ejercicio y ejemplo se usó las siguientes (Pero puede configurarlo a su gusto):

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

1. Copiar el url del repositorio: https://github.com/tonyveas/unexus-servicio.git
2. Abrir el bash de git o cualquier otra herramienta para clonar el repositorio de github.
3. Usamos: git clone https://github.com/tonyveas/unexus-servicio.git
4. Una vez clonado abrimos el respectivo Eclipse IDE.
5. Creamos un workspace.
6. Luego ir a `File > Import`.
7. En la ventana de importación, expandir la carpeta `Maven` y selecciona `Existing Maven Projects`.
8. Haz clic en `Next`.
9. En la siguiente ventana, hacer clic en el botón `Browse` y navegar hasta el directorio del repositorio clonado anteriormente `unexus-servicio/ec-prueba-unexus-servicio-tony-veas`.
10. Haz clic en `Finish`. El proyecto se importará en Eclipse.
11. Y esperamos a que finalice la importación.
12. Actualizar el `application.properties` con las credenciales y las configuraciones de la base de datos.

Aquí hay un ejemplo de configuración de la base de datos:

```properties
# Propiedades para configurar la base de datos
app.datasource.username=postgres
app.datasource.url=jdbc:postgresql://localhost:5432/unexus-database
app.datasource.password=root
app.datasource.driver-class-name=org.postgresql.Driver
```

13. Damos clic derecho en la carpeta de nuestro proyecto ec-prueba-unexus-servicio-tony-veas, Run As, escogemos Maven clean y esperamos que termine.
14. Damos nuevamente clic derecho en la carpeta ec-prueba-unexus-servicio-tony-veas, Run As, escogemos Maven install y esperamos que termine.
15. Dentro de nuestra carpeta/paquete ec-prueba-unexus-servicio-tony-veas, buscamos la clase EcPruebaUnexusServicioTonyVeasApplication.java, damos clic derecho en la clase, seleccionamos Run As y finalmente Java Application. Esperamos que la aplicación inicie.
16. Si revisamos la base de datos, veremos las tablas creadas con los datos de pruebas.

# APIs y funcionalidades

Este servicio proporciona un conjunto de API REST para gestionar los datos de los clientes y sus direcciones. Las funcionalidades incluyen:

- Buscar y obtener un listado de clientes.
- Crear un nuevo cliente con la dirección matriz.
- Editar los datos de un cliente.
- Eliminar un cliente.
- Registrar una nueva dirección para un cliente.
- Listar las direcciones adicionales de un cliente.

Consulte la documentación de la API para obtener más detalles.

# Ejemplos de uso de nuestra API REST

Para una mejor comprensión le invito a ver el siguiente video:

[![Demo Video](http://img.youtube.com/vi/xrFyQja7P3E/0.jpg)](https://youtu.be/xrFyQja7P3E "Uso de la API REST")

Los resultados dependen de los valores que se estén almacenando en la base de datos.

## 1. Buscar cliente

- a) Buscar por numero_identificacion que no existe. Con lo cual nos devolvería vacío o arreglo de JSON vacío.
  ```
  END_POINT (Para numero_identificacion inexistente): http://localhost:8080/clientes?search=4545454545
  ```
- b) Buscar por nombre que no existe. Con lo cual nos devolvería vacío o arreglo de JSON vacío.
  ```
  END_POINT (Para nombres inexistentes): http://localhost:8080/clientes?search=Fer
  ```
- c) Buscar por numero_identificacion que sí existe, usando coincidencia exacta. Con lo cual debería devolver un único valor.
  ```
  END_POINT (Para numero_identificacion exacto existente): http://localhost:8080/clientes?search=0987633333
  ```
- d) Buscar por numero_identificacion que sí existe, usando coincidencias parciales (Ya que se usa un like).
  Con lo cual debería devolver todos los valor que coincidan.
  ```
  END_POINT (Para numero_identificacion que coinciden): http://localhost:8080/clientes?search=09876
  ```
- e) Buscar por nombre que sí existe, usando coincidencia exacta. Con lo cual debería devolver un único valor.
  ```
  END_POINT (Para nombres exactos existente): http://localhost:8080/clientes?search=Lira Pinoargote
  ```
- f) Buscar por nombre que sí existe, usando coincidencias parciales (Ya que se usa un like).
  Con lo cual debería devolver todos los valor que coincidan.
  ```
  END_POINT (Para nombres que coinciden): http://localhost:8080/clientes?search=Lira
  ```
- g) Sin enviarle valor al parámetro de búsqueda.
  Con lo cual debería devolver todos los registros.
  ```
  END_POINT (Forma 1): http://localhost:8080/clientes?search
  END_POINT (Forma 2): http://localhost:8080/clientes?search=
  ```

## 2) Crear clientes

- a) Probando validaciones:

  ```
  END_POINT (Para crear cliente POST): http://localhost:8080/clientes
  ```

  Primero probar poniendo cada campo vacío:

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987611111",
    "names": "Carla Jaime",
    "email": "carla.jaime@ux.ec",
    "cellphone": "0854232102",
    "province": "Provincia Napo",
    "city": "Ciudad de Napo ",
    "address": "Interseccion principal Napo"
  }
  ```

  Luego se debe de probar los campos especiales:

  - identificationType debe ser RUC o CÉDULA.
  - identificationNumber debe tener 10 dígitos.
  - email tiene que tener la estructura de un correo válido.
  - cellPhone debe tener 10 dígitos.

- b) Probando un insert con cédula repetida:

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987611111",
    "names": "Carla Jaime",
    "email": "carla.jaime@ux.ec",
    "cellphone": "0854232102",
    "province": "Provincia Napo",
    "city": "Tena",
    "address": "Calle de las Amazonas"
  }
  ```

- c) Probando un insert correcto (Indicar que por defecto se guarda la dirección como matriz):

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987654321",
    "names": "Carlos Perez",
    "email": "carlos.perez@ux.ec",
    "cellphone": "0985232102",
    "province": "Provincia del Guayas",
    "city": "Guayaquil",
    "address": "Av de las Americas"
  }
  ```

- d) Existe la opción de indicar el parámetro typeAddress, dónde al guardar al cliente se le puede indicar si la dirección que se está pasando en el request es MATRIZ o SUCURSAL:

  Caso 1: typeAddress=MATRIZ

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "3030303030",
    "names": "Andrea Jimenes",
    "email": "andrea.jimenes@ux.ec",
    "cellphone": "0844244102",
    "province": "Provincia Zamora Chinchipe",
    "city": "Ciudad de Zamora Chinchipe",
    "address": "Interseccion principal Zamora Chinchipe",
    "typeAddress": "MATRIZ"
  }
  ```

  Caso 2: typeAddress=SUCURSAL

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "3030303030",
    "names": "Andrea Jimenes",
    "email": "andrea.jimenes@ux.ec",
    "cellphone": "0844244102",
    "province": "Provincia Zamora Chinchipe",
    "city": "Ciudad de Zamora Chinchipe",
    "address": "Interseccion principal Zamora Chinchipe",
    "typeAddress": "SUCURSAL"
  }
  ```

## 3) Crear direcciones para un cliente.

a) Intentando crear una dirección para un cliente que no existe.

```
  END_POINT : http://localhost:8080/clientes/direcciones/100000
```

```json
{
  "province": "Azuay sucursal",
  "city": "Cuenca sucursal",
  "address": "Gran Colombia 1234 sucursal"
}
```

END_POINT (Para crear direcciones): http://localhost:8080/clientes/direcciones/3

b) Probar validaciones

```json
{
  "province": "Azuay sucursal",
  "city": "Cuenca sucursal",
  "address": "Gran Colombia 1234 sucursal"
}
```

c) Probar con los datos correctos. Es importante recalcar que la dirección por defecto se guarda como SUCURSAL.

```json
{
  "province": "Azuay surcursal",
  "city": "Cuenca surcursal",
  "address": "Gran Colombia 1234 surcursal"
}
```

d) Existe un parámetro, llamado typeAddress, el cual puede recibir el valor de SUCURSAL o MATRIZ. Si indicamos como SUCURSAL, la dirección se guarda como sucursal. Si se indica como MATRIZ, se guardará como MATRIZ, siempre y cuando el cliente por alguna razón al inicio no registró su dirección matriz, si ya tiene da el mensaje de error y si no tiene se guarda.

typeAddress=MATRIZ

```json
{
  "province": "Azuay matriz",
  "city": "Cuenca matriz",
  "address": "Gran Colombia 1234 matriz",
  "typeAddress": "MATRIZ"
}
```

typeAddress=SUCURSAL

```json
{
  "province": "Azuay province para sucursal",
  "city": "Cuenca ciudad para sucursal",
  "address": "Gran Colombia 1234 para la sucursal",
  "typeAddress": "SUCURSAL"
}
```

## 4) Editar clientes

- a) Probar editando con un id de cliente que no existe.
  ```
   END_POINT (Para id inexistente): http://localhost:8080/clientes/30
  ```

```json
{
  "identificationType": "CÉDULA",
  "identificationNumber": "0987633333",
  "names": "Lira Pinoargote",
  "email": "Lira.pinoargote@email.com",
  "cellphone": "0996793333",
  "province": "Provincia de Prueba Matriz Lira Pinoargote edit",
  "city": "Ciudad de Prueba Matriz Lira Pinoargote",
  "address": "Calle de Prueba Matriz Lira Pinoargote"
}
```

- b) Probando validaciones: (Similar al insert).

  ```
  END_POINT (Para editar cliente PUT): http://localhost:8080/clientes/{idCliente}
  ```

  Primero probar poniendo cada campo vacío:

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987633333",
    "names": "Lira Pinoargote",
    "email": "Lira.pinoargote@email.com",
    "cellphone": "0996793333",
    "province": "Provincia de Prueba Matriz Lira Pinoargote edit",
    "city": "Ciudad de Prueba Matriz Lira Pinoargote",
    "address": "Calle de Prueba Matriz Lira Pinoargote"
  }
  ```

  Luego se debe de probar los campos especiales:

  - identificationType debe ser RUC o cédula.
  - identificationNumber debe tener 10 dígitos.
  - email se un email válido.
  - cellPhone debe tener 10 dígitos.

  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987633333",
    "names": "Lira Pinoargote",
    "email": "Lira.pinoargote@email.com",
    "cellphone": "0996793333",
    "province": "Provincia de Prueba Matriz Lira Pinoargote edit",
    "city": "Ciudad de Prueba Matriz Lira Pinoargote",
    "address": "Calle de Prueba Matriz Lira Pinoargote"
  }
  ```

- b) Probando un update correcto:
  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "0987633333",
    "names": "Lira Pinoargote",
    "email": "Lira.pinoargote@email.com",
    "cellphone": "0996793333",
    "province": "Provincia de Prueba Matriz Lira Pinoargote edit",
    "city": "Ciudad de Prueba Matriz Lira Pinoargote",
    "address": "Calle de Prueba Matriz Lira Pinoargote"
  }
  ```
- c) Probando un update con una cédula existente:
  ```json
  {
    "identificationType": "CÉDULA",
    "identificationNumber": "4030303030",
    "names": "Lira Pinoargote edit",
    "email": "Lira.pinoargoteedit@email.com",
    "cellphone": "0996790000",
    "province": "Provincia de Prueba Matriz Lira Pinoargote edit",
    "city": "Ciudad de Prueba Matriz Lira Pinoargote edit",
    "address": "Calle de Prueba Matriz Lira Pinoargote edit"
  }
  ```

## 5) Eliminar clientes

- a) Eliminar un cliente que no existe. Debería dar error.
  ```
  END_POINT (Para eliminar cliente DELETE): http://localhost:8080/clientes/1000
  ```
- b) Eliminar un cliente que sí existe. Debería eliminarlo correctamente.
  Se elimina también las direcciones asociadas.
  ```
  END_POINT (Para eliminar cliente DELETE): http://localhost:8080/clientes/1
  ```

## 6) Listar direcciones de cliente

- a) Buscar por id de cliente que no existe. Con lo cual nos devolvería vacío o arreglo de json vacío.
  ```
  END_POINT (Para mostrar direciones existente): http://localhost:8080/clientes/direcciones/100000000
  ```
- b) Buscar por id de cliente que existe. Con lo cual nos devolvería los datos.
  ```
  END_POINT (Para mostrar direciones existente): http://localhost:8080/clientes/direcciones/54
  ```

# Observaciones

Recuerde siempre revisar los registros en la base de datos y compararlos con lo que obtiene en las respuestas del API.

# Ejecución de test y cobertura

Puede realizar la ejecución de los tests y mirar la cobertura siguiendo los pasos que se muestran más abajo, o para una mejor comprensión le invito a ver el siguiente video:

[![Demo Video](http://img.youtube.com/vi/xu9xQcELBUU/0.jpg)](https://youtu.be/xu9xQcELBUU "Ejecución de tests y cobertura")

1. Una vez que se ha configurado la aplicación, siguiendo los pasos previos. Podemos ejecutar los test. Para lo cual nos vamos a src/test/java
2. Damos clic derecho en src/test/java, Coverage As, JUnit Test.
3. Esperamos que termine la ejecución de los test.
4. Finalmente, podemos revisar la pestaña JUnit con la ejecución de los test y la pestaña Coverage que indica la cobertura de los Test.

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
