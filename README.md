# SD3project

[video youtube](https://youtu.be/tme18qPiFcQ)

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

Documentación de servicio interno
Obtener un listado de las categorías de las incidencias (GET): 
https://<ip service>:8443/v1/categorias/
Obtener un listado de incidencias clasificadas como faq’s por termino de búsqueda (GET):
	https://<ip service>:8443/v1/incidencias/faqssearch/<termino de búsqueda>
obtener un listado de incidencias clasificadas como faq’s pertenecientes a una categoría (GET):
	https:// <ip service>:8443/v1/incidencias/faqs/<nombre de la categoria>
Obtener una incidencia por id (GET):
	https:// <ip service>:8443/v1/incidencias/item/<id>
Modificar una incidencia (PUT):
https:// <ip service>:8443/v1/incidencias/modificar
Añadir un usuario nuevo (POST):
	https:// <ip service>:8443/v1/usuarios/adduser
Obtener los roles de usuario(GET):
	https:// <ip service>:8443/v1/roles
Devuelve una lista de prioridades de incidencia (GET):
	https:// <ip service>:8443/v1/prioridades/

Métodos del cliente Rest implementado con RESTTemplate:
	List<Incidencia> getListaIncidencias(String url);
	Incidencia getIncidencia(String url);
	List<CategoriaIncidencia> getListaCategoria(String url);
	List<PrioridadIncidencia> getListaPrioridad(String url);
	List<PrioridadIncidencia> getListaPrioridad(String url);
	Usuario addUsuario(String url,Usuario usuario);
	ResponseEntity<Incidencia> modificarIncidencia(String url, Incidencia incidencia);
 
Virtualización
Vista física
Instancias (servidores virtuales)  VM más RAM para BD 
Creamos una máquina virtual con vagrant Ubuntu 14.04
Creo dos máquinas virtuales con vagrant, una en la carpeta webapp1 donde se subirá la aplicación spring, y otra en la carpeta bd1 que contendrá la base de datos de la aplicación.
Las máquinas virtuales tendrán instalado cada una un SO Ubuntu 14.04. Para ello habrá que utilizar el comando vagrant:
	Vagrant init Ubuntu/trusty32
Se ejecutará dentro de cada una de las carpetas para que se creen los archivos de configuración. De esta forma podremos levantar las máquinas de forma independiente, utilizando el comando vagrant up dentro del directorio correspondiente de la máquina.
A continuación configuramos el archivo vagrantfile de cada máquina para asignarle una IP privada mediante la que se podrá conectar el host.:
Webapp1 ip privada: "192.168.33.10"
Bd1 ip privada: "192.168.33.11"
Instalación de Java en webapp1:
Añadimos el repositorio sudo add-apt-repository ppa:webupd8team/java
Sudo apt-get update
Instalo el jre Sudo apt-get install  -y openjdk-8-jre

Instalo mysql en bd1
Sudo apt-get install mysql-server
Configuro el archivo /etc/mysql/my.cnf para que admita conexiones remotas y así crear una conexión ssh a través de Workbench: cambio la propiedad a bind-address = 0.0.0.0. Lo correcto es que la dirección de la propiedad bind-address fuera la IP de la máquina con la aplicación web que se va a conectar a la base de datos, pero de esta manera, puedo acceder también desde workbench instalado en mi host.

Nos conectamos a la máquina bd1 mediante ssh (vagrant ssh) y accedo a mysql con usuario
	 root (mysql -u root -p)
Se creará una base de datos donde se almacenarán las tablas de la aplicación:
	CREATE DATABASE `gestorincidencias`;
Se creará un usuario para que se conecte la aplicación. Desde Mysql creo el nuevo usuario:
	Create user ‘webapp’ @’%’ identified by ‘Admin’;
Y después le damos todo los privilegios sobre la base de datos que tiene las tablas de la app:
GRANT ALL PRIVILEGES ON gestorinicdenicas.* TO ‘webapp’@’%’;
En el momento en que se conozca la ip del servidor donde está alojada la aplicación web se debería sustituir el % (que permite conectarse a la base de datos a través de ese usuario desde cualquier lado) por esa IP por temas de seguridad.
Por último hago que todo lo anterior tenga efecto con:
	Flush privileges;

Creación del fichero de la aplicación. Jar
Primero modificaremos el properties para cambiar la propiedad “dll-auto” de la base de datos a “none”, ya que es la forma correcta para subirla a producción.
Para construir el jar a través de sts ejecuto run as >mvn build… y ejecuto poniedo como valor de “goal” el valor “clean package”

Para ejecutar la aplicación tenemos en cuenta que el fichero porperties guarda la configuración de desarrollo en local,
spring.datasource.url=jdbc:mysql://localhost/gestorincidencias
spring.datasource.username=root
spring.datasource.password=Admin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
 por lo que tendremos que cambiar los parámetros a través de la línea de comandos en el momento de ejecutar el archivo, por lo tanto, usaremos:
Java -jar <webapp> --server.address=”<ip mv>” –spring.datasource.url=” jdbc:mysql://<ip privada bd1>:3306/gestorincidencias” –spring.datasource.username=”webapp” –spring.datasource.password=”Admin” –spring.jpa.hibernate.ddl-auto=”create”
Con esta configuración creo el esquema de la bd. Hay que finalizar la aplicación y volver a arrancar. Las siguientes veces que arranque la aplicación dejaré el valor de dll-auto a “none” (por defecto en el properties) ya estaría en producción.


Balanceador de carga
Antes de instalar el balanceador vamos a crear una nueva máquina virtual que contendrá también la aplicación, será webapp2 y configuraremos su ip privada como:
	config.vm.network "private_network", ip: "192.168.33.12"
Creamos una nueva máquina virtual Ubuntu 14.04 para instalar el balanceador haproxy y le damos una nueva ip privada editando el vagrantfile:
	config.vm.network "private_network", ip: "192.168.33.13"
Para instalarlo utilizamos los comandos:
	Sudo apt-get -y install haproxy
Una vez que hemos instalado el balanceador deberemos de inicializarlo. Pare ello cambiamos el fichero situado en /etc/default/haproxy:
	Cambio ENABLED=0 a ENABLED=1
Configuramos el balanceador con el fichero /etc/haproxy/haproxy.cfg
	Listen webapp 0.0.0.0:8080
		Mode http
		Stats enable
		Stats uri /haproxy?stats
		Balance roundrobin
		Option httpclose
		Option forwardfor
		Server webapp1 <ip server1>:8080 check
		Server webapp2<ip server2>:8080 check
Y ya puedo iniciar el servicio de balanceador:
	Sudo service haproxy start
La configuración anterior no funciona para balacear servidores con ssl, así que se actualiza a la versión 1.5 de Haproxy para salvar este problema.
Instalo el repositorio
	add-apt-repository ppa:vbernat/haproxy-1.5
actualizo el Sistema y vuelvo a instalar haproxy
	apt-get install haproxy

La configuration final que utilizamos es:
listen webapp 0.0.0.0:8080
        mode http
        stats enable
        stats uri /haproxy?stats
        balance roundrobin
        option http-server-close
        option forwardfor
cookie JSESSIONID prefix nocache
        server webapp1 192.168.33.10:8443 cookie 1 check ssl verify none
        server webapp2 192.168.33.12:8443 cookie 2 check ssl verify none

