## PRACTICA 2 DE INTRODUCCION A CIENCIAS DE LA COMPUTACION

Raúl Alonso Ruiz D'santillana
Introducción a Ciencias de la Computación
Profesor: Salvador López Mendoza
Laboratorio: Rosa Victoria Villa Padilla

# ¿De qué trata el programa?

Este programa en Java trata de emular el interés simple de un monto introducido por el usuario dentro de una cantidad de tiempo.
El usuario primero registra su nombre y después monto deseado. Luego, se le solicita el dia, el mes y la fecha en la cual depositará la inversión.
Posteriormente, se le solicita el dia, mes y año en el cual retirará dicha inversión. Se trabaja en el formato dd/mm/aaaa en ambos casos.

El modelo de este programa se basa en el interés simple, donde una institución bancaria x registra este interés con un interés anual del 12% (también llamado tasa anual fija, de hecho).


# ¿Cuál es la función de los archivos dentro del programa?

El programa utiliza dos archivos para su funcionamiento: Fecha.java y TiempoDeInversion.java.

## Fecha.java

Este archivo es una clase auxiliar para el programa principal. Permite validar, almacenar, comparar y mostrar fechas. Representa días, meses y años con enteros en el formato dd/mm/aaaa.
Esta clase es capaz de:
1. Reconocer años bisiestos.
2. Negar la existencia de un año imposible (como un día cero, un 50 de enero o alguna otra fecha que no coincida al calendario gregoriano).
3. Identificar el nombre de un mes con un número correspondiente.


## TiempoDeInversion.java

Este archivo contiene al método main y es la clase principal. Permite conocer el monto final de una inversión con un interés anual a lo largo de un periodo de tiempo.
Es la encargada de solicitar el nombre al usuario, su monto a pagar, su fecha inicial y final de su inversión; con estos datos, calcula el monto total. I

# ¿Cuáles son los requisitos y pasos que debe seguir cada archivo?

## Fecha.java

a) Crear una fecha inicial. Uno sin parámetros, otro que tome tres enteros (día, mes, año); y un tercero con tres datos: dos enteros (día y año) y una cadena para el nombre del mes.

b) Asignar valor a los campos de una fecha.

c) Obtener los campos de una fecha.

d) Obtener la diferencia, en días, entre dos fechas.

e) Dada una fecha inicial y un número entero que representa una cantidad de días, obtener la fecha que corresponde; por ejemplo: con 3-9-2013 y 5, debe dar 8-9-2013. Con la misma fecha inicial y -2 se debe obtener 1-9-2013.

f) Determinar si dos fechas son iguales.

g) Devolver el número correspondiente a un mes a partir de que reciba una cadena con su nombre.

h) Devolver la cadena correspondiente a un mes a partir de que reciba un número.

i) Dada una fecha, especificar los días transcurridos en el año. Por ejemplo, 30 de diciembre de 2013 debe devolver 364.

j) Representar una fecha en el formato dd/mes/aaaa, donde dd es el día, mes es el nombre del mes y aaaa son cuatro dígitos que representan el año.

## TiempoDeInversion.java

a) Dar bienvenida y solicitar el nombre del inversionista.

b) Recabar el nombre del inversionista.

c) Saludar al inversionista y preguntar por el monto a invertir.

d) Recabar monto.

e) Preguntar por una fecha inicio de inversión.

f) Recabar fecha inicio de inversión.

g) Preguntar por una fecha fin de inversión.

h) Recabar fecha fin de inversión.

i) Mostrar al usuario cuántos días sería su inversión y mostrar su rendimiento al 12 %.

j) Preguntar si desea hacer otra simulación; en caso de que responda sí, volver al paso 3(c), en otro caso, salir de la simulación.
