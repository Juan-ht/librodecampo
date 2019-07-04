Libro de campo es una aplicación nativa Android desarrollada con Java y Kotlin,
con la que poder hacer una gestión diaria de las peonadas de agricultores 
tanto por cuenta ajena como por cuenta propia para que, 
la tarea de llevar a cabo sus peonadas fuera más sencilla, además de una opción para visualizar el tiempo en relación a la ubicación
del usuario, en la que podrá ver el tiempo actual en el momento que lo consulta en el que obtendrá datos de la temperatura, 
estado del cielo y previsión de lluvia, además podrá consultar el tiempo para las próximas 24 horas en intervalos de una hora y 
el tiempo previsto en los próximos 7 días.

Esta aplicación se ha desarrollado en 2 módulos, en los que por una parte está todo el Front-end de la aplicación en Java,
en el que se obtienen datos de la base de datos en tiempo real de Firebase, para más tarde, guardarlos en una base de datos local, 
situada en el otro módulo de la aplicación que se trata de una librería desarrollada en Kotlin, en la que se utiliza Room como ORM
para tener una copia de los datos, si la aplicación se encuentra sin conexión, volviendo al módulo donde se encuentra el Front-end,
también se hace uso de la librería 
Google Analytics de Firebase para conocer las partes que más se usan en la aplicación para obtener un feedback de las partes preferidas 
por los usuarios, a su vez, se utiliza una librería más de Firebase llamada Crashlytics para saber cuando un usuario tiene una fallo
poder tener un detalle del problema que ha tenido y poder solucionarlo de manera sencilla y así, hacer que la experiencia de usuario
sea más satisfactoria, siguiendo con las librerías que tiene esta aplicación, se utiliza Dagger para inyección de dependencias, 
para una mayor eficiencia desarrollando código, se utiliza DataBindig, para poder visualizar las imágenes e iconos se ha hecho uso
de la librería FontAwesome, librería que proporciona iconos de manera gratuita para usar en nuestras aplicaciones.
