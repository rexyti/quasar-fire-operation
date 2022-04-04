# Operación fuego de Quasar
Se crea un programa capaz de interpretar los llamados de auxilio y ubicación de nuestros aliados
## Nivel I
**Donde están nuestros aliados?**

Iniciaremos nuestra labor ubicando donde se encuentran nuestros aliados, haciendo una búsqueda (https://www.101computing.net/cell-phone-trilateration-algorithm/) rápida nos encontramos que partiendo de la distancia de nuestros aliados a los satélites podemos conocer su ubicación mediante _Trilateracion_ los que nos deja con las siguientes ecuaciones:

![image](https://user-images.githubusercontent.com/6185143/160303840-461934a4-bced-4c2b-81ce-f4edee1964e0.png)

![image](https://user-images.githubusercontent.com/6185143/160303851-b54af017-c690-480a-87cb-88786a9149de.png)

reescribiéndola: 

![image](https://user-images.githubusercontent.com/6185143/160303827-59aedac8-d80a-4aa7-9200-e2f19f1d6d9f.png)

Tendríamos como forma de calcular el punto lo siguiente:

![image](https://user-images.githubusercontent.com/6185143/160303885-d3db73eb-bd93-4622-81db-6cae7b8f9386.png)

El problema de la formula anterior reside en que puede arrojar valores incorrectos si las distancias están equivocadas por lo que surge la necesidad de validar el punto calculado. Para la verificar que los cálculos son correctos evaluamos que la distancia entre nuestros satélites y el punto calculado, si coinciden con la distancia que obtuvimos de los mensajes de entrada hemos encontrado a nuestros aliados.

Por último, nos encontramos con un problema técnico, nuestros cálculos son realizados con valores de coma flotante generando imprecisiones pequeñas, para poder comparar de forma correcta los valores obtenidos agregamos una tolerancia al error.

**Que quieren decirnos?**
Tenemos lo siguiente:

- Tenemos una serie de mensajes con interferencia, la interferencia es rellenada con espacios en blanco

- Debemos unificar los mensajes 

- Los mensajes tienen desfase

El proceso a realizar es el siguiente:

1.	Eliminamos todos los espacios en blanco del inicio de cada mensaje

2.	Obtenemos el tamaño array del mensaje con más elementos, este será el tamaño de nuestro mensaje final

3.	Comparamos el tamaño de cada elemento con el tamaño del mensaje final y rellenamos los mensajes con valores vacíos al inicio de cada mensaje hasta que tenga el mismo tamaño que el mensaje final

4.	Para unificar los mensajes recorremos cada uno y comparamos los elementos en cada posición, los elementos vacíos se ignoran. 

5.	Se verifican que los mensajes no vacios sean iguales, si son iguales se unifican si son diferentes se genera error.


## Nivel II

**Creando los servicios**

Se va debe adecuar el desarrollo realizado en el nivel I para ser publicado en la nube, se elige AWS como el ambiente Cloud a utilizar.

**Pasos realizados**

1.	Se crea la estructura de un proyecto _Sprint-boot_

2.	Se crea un archivo de configuración para almacenar la ubicación de los satélites

3.	Se crea un _@Service_ que contiene la lógica para la utilización del MessageProcesor desarrollado en el nivel 1, partiendo de la configuración

4.	Se crea un _@RestController_ para la publicación del servicio

5.	Por ultimo agregamos la configuración de para utilizar _Swagger_ como de documentación del servicio

**Paso a la nube**

Elastic Beanstalk es el servicio de AWS seleccionado para la publicación del servicio por su facilidad de uso, los servicios quedan alojados de la siguiente manera:

- Swagger con la forma de usar el servicio: http://quasarfireoperationtopsecret-env.eba-ptuv6bwz.us-east-1.elasticbeanstalk.com/swagger-ui/

- Endpoint del servicio post: http://quasarfireoperationtopsecret-env.eba-ptuv6bwz.us-east-1.elasticbeanstalk.com/topsecret/


## Nivel III
Documentacion en progreso

-Endpoint del servicio post http://ec2-3-92-174-53.compute-1.amazonaws.com:5000/topsecret_split/{id}
-Endpoint del servicio get http://ec2-3-92-174-53.compute-1.amazonaws.com:5000/topsecret_split/
-url servidor axon http://ec2-3-92-174-53.compute-1.amazonaws.com:8024/
