# SD3project
Nombre app: Gestor de incidencias informáticas
Etidades: usuario; incidencia; categoría de incidencia; estado incidencia; prioridad de incidencia; rol usuario
Descripción:
El usuario tiene un problema informático, entonces da parte al sistema a través de la web creando una incidencia que quedará registrada en el sistema. La incidencia debe de ser asignada a un técnico para que se encargue de solucionarla. El sistema podría implementar un algoritmo simple que gestione esta asignación a través de indicadores sencillos, cómo la carga de trabajo de los técnicos.
El técnico generará un informe por cada incidencia que cierre

Funcionalidad:

El usuario pone una incidencia a través de un formulario web y la incidencia se registra
La aplicación se comunica con el usuario para avisar de que la incidencia ha llegado correctamente
El usuario puede ver el estado de las incidencias que ha puesto.
La aplicación se encarga de asignar un técnico 
El técnico puede consultar su cola de trabajo
El técnico puede buscar entre las incidencias cerradas (informes) por si se el problema ya ha sido solucionado.
El técnico puede comunicarse a través de mail con el usuario si lo necesita 
El técnico debe generar un informe de solución de la incidencia, antes de cerrarla
El usuario puede hacer una reclamación sobre una incidencia creada.

Parte pública: consulta de preguntas frecuentes, incidencias comunes a todos los usuario, buscador
Parte privada: consultas incidencias de un usuario, creación de incidencias, manejo de incidencias, creación de informes, reclamaciones


Equipo:
	Eleazar Francisco López Sosa (ef.lopez@alumnos.urjc.es)
	José Javier Escudero Angulo  (jj.escudero@alumnos.urjc.es)
	Javier Aparicio García (j.apariciog@alumnos.urjc.es)

Pantallazos pantallas públicas
Index.html
![indexhtml](https://github.com/Ele-Jav-Jos-URJC/SD3project/blob/master/indexhtml.png)

Esta página permite buscar por problemas dentro de las incidencias más comunes, bien por términos usando el buscador, bien por los enlaces de las categorías.

Listado de búsqueda
![listado de búsuqeda](https://github.com/Ele-Jav-Jos-URJC/SD3project/blob/master/listadoBusqueda.png)
En esta página se mostrará una lista con los resultados

Información de la Incidencia
![información de la incidencia] (https://github.com/Ele-Jav-Jos-URJC/SD3project/blob/master/infoIncidencia.png)
Esta página ofrece la información más detallada de la solución a la incidencia
