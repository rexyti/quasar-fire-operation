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