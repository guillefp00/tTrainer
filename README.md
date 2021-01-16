# tTrainer
Aplicacion para la gestion diario de las actividades de un entrenador personal y coach nutricional.

**_Notas_**
El proyecto está preparado para ser compilado y ejecutado sin necesidad de herramientas externas si se ejecuta con el wrapper de maven.
Necesita tener instalado docker para ejecutarlo como contenedor.

**Compilar el proyecto**
Desde la carpeta raiz ejecutar:
`mvnw clean install`

**Cargar el proyecto en intellij**
Desde la carpeta raiz ejecutar:
`mvnw idea:idea`

**Ejecutar el proyecto**
Desde la carpeta ttrainer-app:
`..\mvnw spring-boot:run`
ó con Docker:
`docker -d -p 8080:8080 -name=ttrainer-app ttrainer-app`

Cuando haya arrancado se puede entrar desde:
http://localhost:8080/

La base de datos se generará automáticamente en la carpeta data, si se borra se recreará de nuevo en la siguiente ejecución.
Se incluyen usuarios de prueba:
  - entrenador--> user:entrenador pass:123456
  - administrador--> user:soporte pass:123456

