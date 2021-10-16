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

:bulb: El orden de los parámetros no importa.

- http://localhost:{random-port}/profiles?passion=<value\>&monthlySalary=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>&monthlySalary=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?monthlySalary=<value\>&age=<value\>
- http://localhost:{random-port}/profiles?passion=<value\>
- http://localhost:{random-port}/profiles?monthlySalary=<value\>
- http://localhost:{random-port}/profiles?age=<value\>
  

### Capturas de pantalla sobre el resultado de los microservicios

Con todos los parámetros:
![image](https://user-images.githubusercontent.com/46584463/137598078-05de66c9-b29e-48b8-b00f-6cd4b7157ffc.png)


Sólo con la preferencia:
![image](https://user-images.githubusercontent.com/46584463/137597888-6e7f853e-c177-474d-81f3-7182fc430b08.png)


Sólo con la edad:
![image](https://user-images.githubusercontent.com/46584463/137598028-ae21c2be-39db-4d6e-afe5-a5f14df9968c.png)


Sólo con el salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137598018-ecc2eefd-44ab-4518-879f-1b03c8e32f27.png)


Con preferencia y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137598123-97bcdbae-c087-465d-a981-ee0d6588fbc2.png)


Con preferencia y edad:
![image](https://user-images.githubusercontent.com/46584463/137598141-e985c64f-4b85-41c4-9615-fe660d70c08e.png)


Con edad y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137598167-8de4dfe1-bd29-481e-babe-f56c24970faa.png)




## Descripción del segundo microservicio.

### ¿Qué realiza el microservicio?

El microservicio se encarga de encontrar los cajeros automáticos y/o sucursales bancarias de acuerdo a ciertos parámetros.

Los parámetros para el endpoint son los siguientes:

- latitude (latitud, corresponde a GPS)
- longitude (longitud, corresponde a GPS)
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

Con todos los parámetros:
![image](https://user-images.githubusercontent.com/46584463/137604286-3eac901d-3b3c-4a30-84d0-8db04a9ba9e5.png)


Sólo la latitud:
![image](https://user-images.githubusercontent.com/46584463/137604253-38fd9e9f-8ce7-44da-bcc8-df72c3e474ff.png)


Sólo la longitud:
![image](https://user-images.githubusercontent.com/46584463/137604243-5b126392-1e19-4a4b-8df4-51ab852d2137.png)


Sólo el código postal:
![image](https://user-images.githubusercontent.com/46584463/137604233-686def41-09d5-4ab7-b95b-c720764abd2d.png)


Sólo el lugar (Delegación/Estado):
![image](https://user-images.githubusercontent.com/46584463/137601669-2358a0b3-6075-43e2-b7fb-43d34a4c3a60.png)




