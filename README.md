# Gestión de Clientes
## José Ramón Jiménez Reyes

### Spring 1

Debes crear un programa para la gestión de clientes de una empresa.

- Una dirección postal constará de una dirección, una localidad y un código postal.
- Los datos de contacto de un cliente constarán de un teléfono, un correo y una dirección postal.
- Los datos personales de un cliente tendrán un nombre, unos apellidos, un dni y una fecha de nacimiento.
- Los datos de un cliente se compondrán de sus datos personales y sus datos de contacto.
- Un cliente será igual a otro si su dni es el mismo.
- Deberemos podemos añadir clientes, borrar clientes, buscar clientes, obtener todos los clientes y listar los clientes existentes.
- Nunca devolveremos una referencia o nos adueñaremos de la misma en nuestra clase.
- Para ello, por ahora debe utilizar arrays.
- El programa principal mostrará un menú que nos permitirá realizar todas estas acciones.
- Aunque aún no estamos utilizando el patrón MVC, deberás estruturar las clases en una vista y un modelo.

### Spring 2

Ahora se nos pide que eliminemos la restricción del tamaño del array, por lo que deberás sustituir la utilización de array por una estructura dinámica de datos.

### Spring 3

Ahora debes hacer uso de las interfaces e implementar correctamente el patrón MVC.

Para todo ello te propongo el siguiente diagrama de clases:

![Diagrama de clases para GestioClientes](src/main/resources/gestionClientes.png)

## Commit etiquetado (tag) como "array"
Se implementan las clases DAO con `array`.

## Commit etiquetado (tag) como "listas"
Se implementan las clases DAO con `ArrayList`.

## Commit etiquetado (tag) como "MVC"
Se utilizan interfaces y se implementa el patrón MVC.