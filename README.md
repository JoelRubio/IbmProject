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

- http://localhost:{random-port}/profiles?passion=<\value\>&monthlySalary=<value>&age=<value>
- http://localhost:{random-port}/profiles?passion=<value>&monthlySalary=<value>
- http://localhost:{random-port}/profiles?passion=<value>&age=<value>
- http://localhost:{random-port}/profiles?monthlySalary=<value>&age=<value>
- http://localhost:{random-port}/profiles?passion=<value>
- http://localhost:{random-port}/profiles?monthlySalary=<value>
- http://localhost:{random-port}/profiles?age=<value>
  

### Capturas de pantalla sobre el resultado de los microservicios


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
  
- http://localhost:{random-port}/locations?latitude=<value>&longitude=<value>&postalCode=<value>&place=<value>
  
- http://localhost:{random-port}/locations?latitude=<value>
- http://localhost:{random-port}/locations?longitude=<value>
- http://localhost:{random-port}/locations?postalCode=<value>
- http://localhost:{random-port}/locations?place=<value>

### Capturas de pantalla sobre el resultado de los microservicios
