# Taller 04 – POO

## Descripción del proyecto

Programa desarrollado en Java con Interfaz Gráfica de Usuario (GUI) que permite gestionar, organizar y calcular puntuaciones de una colección de cartas de Pokémon TCG. 
El software simula el sistema de administración que Sutrostian y POOsandon necesitan para mantener en orden sus cartas obtenidas mediante sobres y mazos prearmados, aplicando patrones de diseño avanzados (Singleton, Factory, Visitor, Strategy) para lograr una arquitectura robusta y escalable.

## Integrantes

* **Cristóbal Nicolás Chepilla Arriagada**
  * **RUT:** 21.873.055-8

## Estructura del proyecto

### Clases principales

* **Main.java**
  * Punto de entrada de la aplicación. Se encarga de inicializar y lanzar el entorno visual de forma segura.
* **ColeccionSystem.java**
  * Clase controladora del sistema basada en el patrón **Singleton**. Gestiona la colección de cartas en memoria y coordina la persistencia de datos.
* **Carta.java (Abstracta)**
  * Clase base del modelo que define los atributos comunes de toda carta (Nombre, Rareza, Tipo) y el contrato para la aceptación de visitantes.
* **Pokemon.java / Item.java / Supporter.java / Energy.java**
  * Subclases específicas que modelan los comportamientos y atributos adicionales de cada tipo de carta según el juego.
* **CartaFactory.java**
  * Fábrica centralizada del patrón **Factory Method** que se encarga de la instanciación limpia de las subclases de cartas a partir de los datos leídos.
* **Visitor.java / PoderVisitor.java**
  * Componentes del patrón **Visitor** utilizados para separar el algoritmo de cálculo de poder de la estructura interna de las cartas.
* **OrdenamientoStrategy.java (y sus implementaciones concretas)**
  * Interfaces y clases del patrón **Strategy** que permiten intercambiar dinámicamente el algoritmo de ordenación de la lista en la interfaz.
* **VentanaPrincipal.java / DialogoDetalleCarta.java**
  * Componentes visuales desarrollados en Swing que le dan vida a la GUI mediante un sistema interactivo de pestañas y ventanas emergentes.

## Archivos utilizados

* **Sobres.txt:** Archivo de texto plano que almacena los registros de las cartas de la colección en un formato estructurado (`NombreCarta;Rareza;Tipo;...`).

## Instrucciones de ejecución

1. Abrir el proyecto en su IDE de preferencia (Eclipse, IntelliJ IDEA o NetBeans).
2. Verificar que el archivo `Sobres.txt` y la carpeta `imagenes/` se encuentren exactamente en la raíz del proyecto (fuera de la carpeta `src`).
3. Asegurarse de incluir una imagen por defecto llamada `default.png` dentro de la carpeta de imágenes para prever cartas sin ilustración manual.
4. Ejecutar la clase `Main.java`.
5. Interactuar con la aplicación mediante sus pestañas y ventanas gráficas flotantes.

## Funcionalidades implementadas

* **Pestaña de Administración (CRUD Completo):**
  * **Agregar Carta:** Formulario dinámico que adapta sus campos según el tipo de carta seleccionado.
  * **Eliminar Carta:** Remueve el objeto seleccionado de la colección sin borrar físicamente su recurso gráfico.
  * **Modificar Carta:** Permite la edición exclusiva de los atributos adicionales propios del tipo de carta, bloqueando campos estáticos (Nombre y Rareza) según la pauta.
* **Pestaña de Visualización de Colección:**
  * **Mecanismo de Ordenación (Sort):** Reorganiza la colección de manera fluida bajo tres criterios intercambiables: por Nombre, por Rareza (de mayor a menor) y por Poder.
  * **Visualización Ampliada:** Ventana de detalle emergente (JDialog) gatillada por interacción con la lista, que despliega de forma ordenada los atributos, la imagen correspondiente y el poder calculado de la carta.
* **Cálculo Avanzado de Puntuaciones:** Motor matemático desacoplado mediante patrones que calcula el poder exacto de cada carta siguiendo las fórmulas oficiales requeridas.
* **Persistencia Automática:** Carga automática de registros al arrancar y sobrescritura inmediata en tiempo de ejecución (`Sobres.txt`) ante cualquier cambio en la colección, manteniendo intacto el formato original.
* **Control de Errores Robustez:** Validación de campos mediante bloques `try-catch` para evitar caídas imprevistas ante ingresos de datos inválidos por parte del usuario.
