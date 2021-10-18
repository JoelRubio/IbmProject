# IBM Project
Repositorio que contiene dos microservicios para el proyecto de IBM.

## Descripción del primer microservicio sobre tipos de tarjetas de crédito.

### ¿Qué realiza el microservicio?

El microservicio se encarga de encontrar el tipo de tarjeta de crédito acorde a las características del cliente, ya sea la preferencia, el salario mensual, la edad o todos los parámetros.

Los parámetros para los endpoints son los siguientes:

- passion (preferencia)
- monthlySalary (salario mensual)
- age (edad)

Estos parámetros pueden ser opcionales pero al menos debe de haber un parámetro para que funcione el microservicio.

### ¿Cómo interactuar con ellos?

El microservicio interactua a través de otro servicio llamado Gateway, el cual se encargará de enviar y recibir las peticiones desde y hacia el microservicio.
Es por ello que sólo se necesita la dirección del Gateway y especificar el nombre del microservicio al cual se quiere conectar, además de sus respectivos parámetros para la búsqueda.


### Endpoints

:bulb: El orden de los parámetros no importa.

- http://localhost:8089/profile-credit-card-ws/search?passion=<value\>&monthlySalary=<value\>&age=<value\>
- http://localhost:8089/profile-credit-card-ws/search?passion=<value\>&monthlySalary=<value\>
- http://localhost:8089/profile-credit-card-ws/search?passion=<value\>&age=<value\>
- http://localhost:8089/profile-credit-card-ws/search?monthlySalary=<value\>&age=<value\>
- http://localhost:8089/profile-credit-card-ws/search?passion=<value\>
- http://localhost:8089/profile-credit-card-ws/search?monthlySalary=<value\>
- http://localhost:8089/profile-credit-card-ws/search?age=<value\>
  

### Capturas de pantalla sobre el resultado de los microservicios

Con todos los parámetros:
![image](https://user-images.githubusercontent.com/46584463/137610002-81fed063-2884-47da-b950-8a66dbd1b7a6.png)


Sólo con la preferencia:
![image](https://user-images.githubusercontent.com/46584463/137610019-7cc073a6-422d-4513-b3a7-1243708a5ca1.png)


Sólo con la edad:
![image](https://user-images.githubusercontent.com/46584463/137610034-5cca670b-fcb3-4141-901e-d3d5f606e384.png)


Sólo con el salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137610050-5d7c04d0-8e79-4554-85e2-a91241dc4ac7.png)


Con preferencia y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137610075-f27c6620-2cc9-481e-92dd-a65fd3e70b98.png)


Con preferencia y edad:
![image](https://user-images.githubusercontent.com/46584463/137610088-45e16c47-ccf1-4785-b45e-828d39dbbabf.png)


Con edad y salario mensual:
![image](https://user-images.githubusercontent.com/46584463/137610097-595e43a2-135a-4362-a055-52d1d00cc14a.png)




## Descripción del segundo microservicio sobre encontrar un ATM o sucursal.

### ¿Qué realiza el microservicio?

El microservicio se encarga de encontrar los cajeros automáticos y/o sucursales bancarias de acuerdo a ciertos parámetros, ya sea por medio del GPS (latitud y/o longitud), código postal, el lugar (Delegación/Estado) o todos los parámetros.

Los parámetros para los endpoints son los siguientes:

- latitude (latitud, corresponde a GPS)
- longitude (longitud, corresponde a GPS)
- postalCode (código postal)
- place (Delegación/Estado)

Estos parámetros pueden ser opcionales pero al menos debe de haber un parámetro para que funcione el microservicio.

### ¿Cómo interactuar con ellos?

El microservicio interactua a través de otro servicio llamado Gateway, el cual se encargará de enviar y recibir las peticiones desde y hacia el microservicio.
Es por ello que sólo se necesita la dirección del Gateway y especificar el nombre del microservicio al cual se quiere conectar, además de sus respectivos parámetros para la búsqueda.


### Endpoints

:bulb: El orden de los parámetros no importa.
  
- http://localhost:8089/bank-entity-ws/search?latitude=<value\>&longitude=<value\>&postalCode=<value\>&place=<value\>
- http://localhost:8089/bank-entity-ws/search?latitude=<value\>
- http://localhost:8089/bank-entity-ws/search?longitude=<value\>
- http://localhost:8089/bank-entity-ws/search?postalCode=<value\>
- http://localhost:8089/bank-entity-ws/search?place=<value\>

### Capturas de pantalla sobre el resultado de los microservicios

Con todos los parámetros:
![image](https://user-images.githubusercontent.com/46584463/137771223-484844a4-c634-465b-866a-30a0e7316972.png)


Sólo la latitud:
![image](https://user-images.githubusercontent.com/46584463/137644284-7a1ddf3c-ddee-4b1a-b2f6-e1e14d2d6faa.png)


Sólo la longitud:
![image](https://user-images.githubusercontent.com/46584463/137644294-a347d0b4-3bae-409a-8d24-81e597f2dea0.png)


Sólo el código postal:
![image](https://user-images.githubusercontent.com/46584463/137644305-3ef0ab8b-c920-4af0-85b5-064496bfd2e8.png)


Sólo el lugar (Delegación/Estado):
![image](https://user-images.githubusercontent.com/46584463/137644325-79c596df-c20c-4902-9642-45ba9351a6ea.png)

:bulb: También es posible realizar combinaciones con los parámetros, pero debido a la brevedad del archivo se omitieron esos casos.




