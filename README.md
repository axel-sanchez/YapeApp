# YapeApp
Éste es el proyecto prueba de Yape, con esta app se puede ver un listado de recetas, acceder para ver sus detalles y abrir un mapa de su ubicación.

# Experiencia de usuario
Este proyecto contiene las siguientes características:

* La pantalla principal donde se ve un listado de recetas.
* Una vista con una receta específica con su descipción completa (se accede seleccionado una receta del listado de la primer pantalla)
* La vista de un mapa con un marcador de donde es la receta.
# Capturas de pantalla

<p align="center">
  <img width="270" height="555" src="https://user-images.githubusercontent.com/51034538/226547042-4df74b78-f0d5-47c6-8006-e5b35ebdca50.jpg">
  <img width="270" height="555" src="https://user-images.githubusercontent.com/51034538/226547081-368df007-949d-4e1a-a520-38f480efaa97.jpg">
  <img width="270" height="555" src="https://user-images.githubusercontent.com/51034538/226547105-8dec2e18-1b33-4aac-8ea1-48486ed28c41.jpg">
</p>

## Guía de implementación
Trabajo con un generador de mocks que me devuelve el listado de recetas, la url es http://demo9207776.mockable.io/recipes

### Arquitectura
Este proyecto implementa el patrón de arquitectura MVVM y sigue buenas prácticas de Clean Architecture para hacer un código más independiente, mantenible y sencillo.

#### Capas
* Presentation: Fragments y Activities
* Data: contiene la implementación del repositorio y los sources donde se conecta con la api y con la base de datos
* Domain: contiene los casos de uso y la definición del repositorio
Este proyecto usa ViewModel para almacenar y manejar datos, así como comunicar cambios hacia la vista.

### Administrador de solicitudes: Retrofit

Este proyecto utiliza Retrofit para mostrar los productos desde una API.

### Inyección de dependencia - Dagger

Este proyecto utiliza Dagger para gestionar la inyección de dependencia.

### Persistencia de datos - Room

Este proyecto utiliza la base de datos de Room para almacenar las recetas.

### Testing

La app posee tests hechos con JUnit4 y Espresso

### Patrones de diseño

Utilizo algunos patrones de diseño como Observer, Singleton, Builder

# Guía de instalación
En caso de no tener instalado Android Studio, descargue la última versión estable. Una vez que tenemos el programa instalado vamos a Get from Version Control y vamos a pegar https://github.com/axel-sanchez/YapeApp.git Una vez hecho eso se va a clonar el proyecto, lo que resta sería conectar un celular y darle al botón verde de Run 'app'
