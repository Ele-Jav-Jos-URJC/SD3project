package es.gestorincidencias.rest.controladores;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * controlador que proporciona la comunicación entre servidor y cliente mediante REST
 * @author Javier Aparicio
 *
 */

import com.fasterxml.jackson.annotation.JsonView;

import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.entidades.Usuario;
import es.gestorincidencias.servicios.PublicService;
@RestController
@RequestMapping (value="/v1/incidencias")
public class IncidenciasController {

	@Autowired
	private PublicService pService;
	
	/**
	 * Servicio rest que devuelve todas las incidencias
	 * @return List<Incidencia>
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="", method=RequestMethod.GET)
	public List<Incidencia> getIncidencias(){
		return pService.getIncidencias();
	}
	
	/**
	 * Servicio REST que devuelve una incidencia por su id
	 * @param idIncidencia
	 * @return Incidencia
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="/item/{idIncidencia}", method=RequestMethod.GET)
	public Incidencia getIncidencias(@PathVariable ("idIncidencia") long idIncidencia){
		return pService.getIncidencia(idIncidencia);
	}
	/**
	 * Servicio REST que devuelve las incidencias pertenecientes a una categoría
	 * @param categoria
	 * @return
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="/{categoria}", method=RequestMethod.GET)
	public List<Incidencia> getIncidencias(@PathVariable("categoria") String categoria){
		return pService.getIncidenciasByCategoria(categoria);
	}
	
	/**
	 * Servicio REST que devuelve las incidencias pertenecientes a una categoría
	 * @param categoria
	 * @return
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="/faqs/{categoria}", method=RequestMethod.GET)
	public List<Incidencia> getFaqs(@PathVariable("categoria") String categoria){
		return pService.getFaqByCategoria(categoria);
	}
	
	/**
	 * Servicio REST que devuelve las incidencias a través de una cadena de búsqueda
	 * @param categoria
	 * @return
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="/faqssearch/{search}", method=RequestMethod.GET)
	public List<Incidencia> getFaqsBySearch(@PathVariable("search") String search){
		return pService.getFaqBySearch(search);
	}
	
	/**
	 * Servicio REST que devuelve las incidencias que ha puesto un usuario
	 * @param id
	 * @return List<Incidencia>
	 */
	@JsonView(Incidencia.Principal.class)
	@RequestMapping (value="/user/{user}", method=RequestMethod.GET)
	public List<Incidencia> getIncidenciasByUser(@PathVariable("user") long id){
		return pService.getIncidenciasByUser(id);
	}
	
	@RequestMapping(value="/modificar", method=RequestMethod.PUT)
	public ResponseEntity<Incidencia> putIncidencia(@RequestBody Incidencia incidencia){
		pService.modficarIncidencia(incidencia.getId(), incidencia.getInforme(), incidencia.isFaq());
		return new ResponseEntity<>(incidencia,HttpStatus.OK);
	}
}
