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

Despliegue de la aplicación
Para crear un fichero .jar con la aplicación utililzo el IDE, a través de Run As> Maven build... para que limpie y compile la aplicación utilizo el "goal" "clean package".
Para la virtualziación de la máquina descargo e instalo los programas Vagrat y virtual box en SO Windows 10.
A través de la consola del sitema crearé la máquina virtual que utilizaré para el despliegue. Para ello nos situamos en el directorio Vagrant y creo una careta donde se desplegará la máquina. Para ello se utiliza el comando:
vagrant init ubuntu/trusty32
esto creará el fichero vagrantfile con la configuración para levantar una máquina ubuntu 14.04.5 LTS. Antes de nada se descomenta la línea del fichero
config.vm.network "private_network", ip: "192.168.33.10"
que permitirá tener una ip privada para poder conectar desde la máquina anfitriona (tanto al puerto web como al de la base de datos).
Después se levantará la máquina con el comando "vagrant up"
Con la máquina levantada accederemos al servidor Ubuntu por SSH, utilizando el comando vagrant ssh
Una vez dentro de la máquina instalaremos el jre 8 utilizando el comando apt-get. Para instalar esta vesión de Java necesitamos instalar un paquete sudo apt-add-repository ppa:webupd8team/java y después actualizamos con sudo apt-get update. Ahora podemos instalar jre 8 con sudo apt-get install -y openjdk-8-jre. De esta forma ya podemos ejecutar archivos java.
A continuación instalaremos la base de datos mysql y la configuraremos. Después creamos el esquema donde trabajará nuestra aplciación web
A través de la carpeta compartida de vagrant podemos pasar el archivo jar con nuestra aplciación web, y desde la máquina virtual la podemos ejecutar para que se despliegue el servidor web y la aplicación. De esta manera tendremos desplegadas en la máquina Ubuntu, tanto el servidor web como la base de datos.
Podremos acceder desde la máquina anfitriona utilizando la ip privada que configuramos en el archivo vagranfile.

