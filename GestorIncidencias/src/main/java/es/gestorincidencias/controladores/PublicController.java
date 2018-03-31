package es.gestorincidencias.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import es.gestorincidencias.servicios.PublicService;
import es.gestorincidencias.entidades.*;
import java.util.Date;

/**
 * @author CAN
 *
 */
@Controller
public class PublicController {
	@Autowired
	private PublicService publicService;
	
	@RequestMapping("/")
	public String cargarIndex(Model model) {
		List<CategoriaIncidencia> categorias=publicService.getCategorias();
		model.addAttribute("results",categorias);
		return "index";
	}
	
	@GetMapping("/search")
	public String buscarIncidencia(Model model, @RequestParam String busqueda) {
		List<Incidencia> incidencias=publicService.getFaqBySearch(busqueda);
		model.addAttribute("results",incidencias);
		return "listaincidencias";

	}
	
	@GetMapping("/categoria")
	public String cargarListadoFaq(Model model,@RequestParam String id) {
		List<Incidencia> incidencias=publicService.getFaq(id);
		model.addAttribute("results",incidencias);
		return "listaincidencias";
	}
	
	@GetMapping("/incidencia")
	public String cargarIncidencia(Model model,@RequestParam String id) {
		Incidencia incidencia=publicService.getIncidencia(id);
		model.addAttribute("results",incidencia);
		return "incidencia";
	}

	
	@RequestMapping("/grabausuario")
	public String GrabaUsuario(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String mail,@RequestParam String pass,@RequestParam String rol) {

		Usuario usuario= publicService.setUsuario(nombre,apellido,mail,pass,rol);
	//	model.addAttribute("results",usuario);
		return "datosexito";
	}
	
	@RequestMapping("/guardarincidencia")
	public String AltaIncidencia(Model model,@RequestParam String problema, @RequestParam String categoria,@RequestParam boolean gender) {

		Incidencia inci= publicService.setIncidencia(problema,categoria,gender);
		model.addAttribute("results",inci);
		return "confirmacion";
	}

	@RequestMapping("/nuevousuario")
	public String AltaUsuario(Model model) {

		return "nuevousuario";
	}
	
	@RequestMapping("/nuevaincidencia")
	public String AltaIncidencia(Model model) {

		return "nuevaincidencia";
	}
	
	@RequestMapping("/listaincidencias")
	public String ListaIncidencia(Model model) {

		return "listaincidencias";
	}
	

	@RequestMapping("/confirmacion")
	public String confirmacion(Model model,@RequestParam Incidencia incidencia) {
		
		Incidencia incidencia2=publicService.getIncidencia(incidencia);
		model.addAttribute("results",incidencia2);

		return "confirmacion";
	}
	
}
