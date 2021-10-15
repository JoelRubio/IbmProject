# IbmProject
Repositorio que contiene dos microservicios principales para el proyecto de IBM.

## Descripción del primer microservicio.

### ¿Qué realiza el microservicio?

El microservicio se encarga de encontrar el tipo de tarjeta de crédito acorde a las características del cliente.

Los parámetros para el endpoint son los siguientes:

- passion (preferencia)
- monthlySalary (salario mensual)
- age (edad)

Estos parámetros pueden ser opcionales pero al menos debe de haber un parámetro para que funcione el microservicio.


### Endpoints

:bulb: El puerto se asigna de manera aleatoria en tiempo de ejecución.

- http://localhost:{random-port}/profiles?passion=<value\>&monthlySalary=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>&monthlySalary=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?monthlySalary=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>
- http://localhost:{random-port}/profiles?monthlySalary=<value\>
- http://localhost:{random-port}/profiles?age=<value\>
  

### Capturas de pantalla sobre el resultado de los microservicios

Con todos los parámetros:
![image](https://user-images.githubusercontent.com/46584463/137561424-3724e306-9b6d-4b98-a4bc-75360d7a98e4.png)

Sólo con la preferencia:
![image](https://user-images.githubusercontent.com/46584463/137561841-8dd8c0dd-abbc-4357-8410-4f5d545445c3.png)

Sólo con la edad:
![image](https://user-images.githubusercontent.com/46584463/137561863-c0062237-1661-447a-9416-5cd915738e51.png)

Sólo con el salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137561893-e80c96cb-40dc-4d7d-9bc6-4619a0deffce.png)

Con preferencia y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137561659-efed6c20-5da7-42d6-a90f-1b65bfd27fa3.png)

Con preferencia y edad:
![image](https://user-images.githubusercontent.com/46584463/137561701-be0071a0-b2d2-4c84-9c56-ea0dd4fe7a83.png)

Con edad y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137561728-68fb74f3-8c87-408f-b2a3-e0e8f05af08c.png)



## Descripción del segundo microservicio.

### ¿Qué realiza el microservicio?

El microservicio se encarga de encontrar los cajeros automáticos y/o sucursales bancarias de acuerdo a ciertos parámetros.

Los parámetros para el endpoint son los siguientes:

- latitude (latitud, corresponde a GPS)
- longitud (longitud, corresponde a GPS)
- postalCode (código postal)
- place (Delegación/Estado)

Estos parámetros pueden ser opcionales pero al menos debe de haber un parámetro para que funcione el microservicio.

### Endpoints

:bulb: El puerto se asigna de manera aleatoria en tiempo de ejecución.
  
- http://localhost:{random-port}/locations?latitude=<value\>&longitude=<value\>&postalCode=<value\>&place=<value\>
  
- http://localhost:{random-port}/locations?latitude=<value\>
- http://localhost:{random-port}/locations?longitude=<value\>
- http://localhost:{random-port}/locations?postalCode=<value\>
- http://localhost:{random-port}/locations?place=<value\>

### Capturas de pantalla sobre el resultado de los microservicios
