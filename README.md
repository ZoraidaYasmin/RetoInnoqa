# RetoInnoqa

Detalle de configuración de la aplicación:
El proyecto esta realizado sobre Maven. A continuacion se brinda la configuracion necesaria para la instalacion.

1. Se descarga maven a traves de su pagina: *https://maven.apache.org/download.cgi*
2. Se configura en variables de entorno la version de maven que se ha descargado.
Ejemplo:
Variable: *MAVEN_HOME*
Valor: *C:\apache-maven-3.8.6* (ruta de instalacion del maven)

3. Al abrir el proyecto, en este caso utilice el IDE Intelij IDEA reconoce automaticamente que se utiliza Maven. Pero tambien se puden ejecutar estos comandos adicionales para la configuracion del proyecto.

Compilar el proyecto: *mvn clean install*
Comando para ejecutar los test unitarios: *mvn test*
En el caso de querer compilar el proyecto sin los test unitarios: *mvn clean install -DskipTests*
 
Detalle de construcción de aplicación:

1. Crear un repositorio github en público para el acceso al código.

2. Crear un proyecto spring boot con la herramienta spring initializer para agregar las dependencias necesarias, en este caso se agregó la dependencia de JPA, H2, DEVELOPERTOOLS, STARTER WEB, LOMBOK, JUNIT, MOCKITO, LOG4J. Estas dependencias se visualizan en el archivo pom.xml.

3. Se comienza a crear la estructura de paquetes. En este caso el paquete Controller, Entity, Repository, Service, Util.

4. Inicialmente se creó la configuración necesaria en el archivo aplication.properties para usar la base de datos H2, se configuró la ruta de la consola, el driver, el user y un password. Asi mismo se asignó un puerto específico en la cual se levantaría la consola, en este caso el 9090.
La ruta para acceder a la consola es: http://localhost:9090/h2-console

5. Se creó dos archivos llamados data.sql y schema.sql. En el archivo schema se encuentra la creación de la tabla PRICES y en data.sql se encuentra las inserciones de datos a la tabla. Estos archivos se crearon para que al levantar la aplicación se inicialice con los datos brindados del ejemplo. Esta tabla puede ser visualizada cuando se ingresa a la consola del H2.

6.Despues, se creó la entidad Prices, agregue los atributos necesarios de acuerdo a la base de datos y con las herramientas que proporciona lombok utilice la anotacion @Getter, @Setter, @Entity y @Table para definir que es una entidad, una tabla de base de datos y los getter y setter para utilizar los atributos. Se agregaron también otras anotaciones para el facil uso de los atributos.

7. Luego, se creo la clase Repository, se extiende de JPARepository para que permita acceder a los metodos de CRUD que facilita el mismo spring JPA. En esta clase se definio la busqueda de atributos que se requerían de acuerdo a lo solicitado en el reto.

8. Despues, dentro del paquete Service, inicialmente se creo la interface, donde coloqué los metodos que seréan necesarios para luego realizar su implementacién. En este caso se considero un CRUD completo de manera adicional a lo requerido.
En la clase Impl se coloco toda la logica necesaria de acuerdo a los metodos creados en el interface. Se instancia al repository y se agrega la anotacion @Service para indicar el tipo de clase que es.
En los metodos se consideraron respuestas de errores de tipo HTTP, algunas excepciones y validaciones.

9. Se creó un paquete adicional llamada Exception, en este paquete se colocaron las excepciones necesarias para cada metodo requerido en el Implement.

10. Se creó un paquete adicional llamado Util, en este caso fue para agregar validación necesaria que se usaria dentro del Implement.

11. Luego, se realiza la clase Controller, este caso usamos las anotaciones de spring para identificar que es de tipo rest, con la anotacion @RestController, se le declara un path con la anotacion @RequestMapping.
Se crea las apis de acuerdo a la función que va a cumplir cada uno, ya sea un POST, PUT, GET, DELETE. Adicional, se declara como @PathVariable o @RequestParam para que se extraiga los valores de la uri. Tambien, se consideraron excepciones en cada metodo.

12. Las pruebas de las apis desarrolladas es en postman, adjunto el collection de pruebas.

13. Se realiza las pruebas unitarias dentro de la carpeta TEST, se utiliza la anotación @Mock para el service, para mockear la interface y la anotación @InjectMocks para inyectar el objeto simulado, en este caso del service.
Se crearon los 5 test solicitados de acuerdo a las casuisticas. Ademas, se agregaron metodos de assertions para validar el estado de la respuesta HTTP.

14. Finalmente, de manera adicional agregue configuracion de swagger para documentar las apis. se agrego una dependencia en el pom de OPEN API, tambien se agrego una configuracion en el archivo application.properties indicando la ruta del path del controlador para que reconozca las apis del controlador y para poder visualizarlo se ingresa a: http://localhost:9090/doc/swagger-ui/
