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
![image](https://user-images.githubusercontent.com/46584463/137562756-58c7e2c7-e155-44be-9da7-c2228f977a1d.png)


Sólo la latitud:
![image](https://user-images.githubusercontent.com/46584463/137562389-5c2d6199-d391-4351-af74-c4e42d3548aa.png)


Sólo la longitud:
![image](https://user-images.githubusercontent.com/46584463/137562553-d53607ed-2664-4b31-ab40-ee48f6bdd7ea.png)


Sólo el código postal:
![image](https://user-images.githubusercontent.com/46584463/137562308-ca043416-c16b-4d30-9d21-7fbc8d852010.png)


Sólo el lugar (Delegación/Estado):
![image](https://user-images.githubusercontent.com/46584463/137562210-81a714d4-7805-4b64-a172-14cdc1db42d4.png)



