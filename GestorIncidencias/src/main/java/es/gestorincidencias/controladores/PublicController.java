
package es.gestorincidencias.controladores;
import org.springframework.stereotype.Controller;
import java.text.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import es.gestorincidencias.servicios.PublicService;
import es.gestorincidencias.entidades.*;
//import es.gestorincidencias.rest.cliente.IncidenciasCliente;
import org.springframework.security.web.csrf.CsrfToken;
import es.gestorincidencias.rest.cliente.ClienteRest;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 * @author CAN
 *@author Javier Aparicio
 */
@Controller
public class PublicController {
	
	private static final String SERVER="https://localhost:8443/";
	
	@Autowired
	private PublicService publicService;
	
	@Autowired
	private ClienteRest clienteRest;
	
	
	@RequestMapping("/")
	public String cargarIndex(Model model) {
		//Sin REST
		List<CategoriaIncidencia> categorias=publicService.getCategorias();
		
		//con REST
		//List<CategoriaIncidencia> categorias=clienteRest.getListaCategoria(SERVER+"v1/categorias");
		model.addAttribute("results",categorias);
		return "index";
	}
	
	@GetMapping("/search")
	public String buscarIncidencia(Model model, @RequestParam String busqueda) {
		//sin REST
		List<Incidencia> incidencias=publicService.getFaqBySearch(busqueda);
		
		//con REST
		//List<Incidencia> incidencias=clienteRest.getListaIncidencias(SERVER+"v1/incidencias/faqssearch/"+busqueda);
		model.addAttribute("results",incidencias);
		return "listaincidencias";

	}
	
	@GetMapping("/categoria")
	public String cargarListadoFaq(Model model,@RequestParam int id) {
		//sin REST
		List<Incidencia> incidencias=publicService.getFaqByCategoria(id);
		
		//con REST
		//List<Incidencia> incidencias=clienteRest.getListaIncidencias(SERVER+"v1/faqs/"+publicService.getCategoria(id).getCategoria());
		model.addAttribute("results",incidencias);
		
		return "listaincidencias";
	}
	
/*	@GetMapping("/incidencia")
	public String cargarIncidencia(Model model,@RequestParam String id) {
		Incidencia incidencia=publicService.getIncidencia(id);
		model.addAttribute("results",incidencia);
		return "incidencia";
	}
*/
	@GetMapping("/incidencia")
	public String cargarIncidencia(Model model,@RequestParam long id,Usuario usuario,HttpServletRequest request) {
	//		return "incidencia";
		Incidencia incidencia=publicService.getIncidencia(id);
		
		/*con REST
		 * List<Incidencia> incidencias=clienteRest.getListaIncidencias(SERVER+"/v1/incidencias/item/"+id);
		Incidencia incidencia=null;
		if(incidencias.size()>0) {
			incidencia=incidencias.get(0);
		}*/
		
		if(request.isUserInRole("ADMIN") || request.isUserInRole("TECH") || request.isUserInRole("USER")) {
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			if(request.isUserInRole("ADMIN")) {
				model.addAttribute("token", token.getToken());
				model.addAttribute("results",incidencia);
				model.addAttribute("admin","Rol administrador");
				if (publicService.getIncidenciaFechaCierre(incidencia)==null) {
					model.addAttribute("results.fechaCierre"," ");
				}
				if (publicService.getIncidenciaisFaq(incidencia)==true) {
					model.addAttribute("results.isFaq"," SI");
				}else {model.addAttribute("results.isFaq"," NO");}
				
			}else if (request.isUserInRole("TECH")) {
				model.addAttribute("token", token.getToken());
				model.addAttribute("tech","Rol técnico");
				model.addAttribute("results",incidencia);
				if (publicService.getIncidenciaFechaCierre(incidencia)==null) {
					model.addAttribute("results.fechaCierre"," ");
				}
				if (publicService.getIncidenciaisFaq(incidencia)==true) {
					model.addAttribute("results.isFaq"," SI");
				}else {model.addAttribute("results.isFaq"," NO");}
			}else {
				model.addAttribute("token", token.getToken());
				model.addAttribute("user","Rol usuario");
				model.addAttribute("results",incidencia);
				if (publicService.getIncidenciaFechaCierre(incidencia)==null) {
					model.addAttribute("results.fechaCierre"," ");
				}
				if (publicService.getIncidenciaisFaq(incidencia)==true) {
					model.addAttribute("results.isFaq"," SI");
				}else {model.addAttribute("results.isFaq"," NO");}
			}
			return "/incidencia";
		}else
			{
			model.addAttribute("publico","Zona pública");
			model.addAttribute("results",incidencia);
			if (publicService.getIncidenciaFechaCierre(incidencia)==null) {
				model.addAttribute("results.fechaCierre"," ");
			}
			if (publicService.getIncidenciaisFaq(incidencia)==true) {
				model.addAttribute("results.isFaq"," SI");
			}else {model.addAttribute("results.isFaq"," NO");}
			
			return "incidencia";
			}
	}
	@RequestMapping("/cierreincidencia")
	public String cierreIncidencia(Model model,@RequestParam String solucion,@RequestParam long id,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		Incidencia incidencia=publicService.getIncidencia(id);
		 
		incidencia=publicService.setIncidencia(solucion,incidencia);
			return "/datosexito";

	}
	
		
	/**
	 * @param nombre
	 * @param apellido
	 * @param mail
	 * @param pass
	 * @param rol
	 * @return
	 */
	@RequestMapping("/grabausuario")
	public String grabaUsuario(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String mail,@RequestParam String pass,@RequestParam String rol) {

		Usuario usuario= publicService.setUsuario(nombre,apellido,mail,pass,rol);
	//	model.addAttribute("results",usuario);
		return "datosexito";
	}
	
	@RequestMapping("/guardarincidencia")
	public String altaIncidencia(Model model,@RequestParam String problema, @RequestParam String categoria,@RequestParam boolean gender,HttpServletRequest request) throws ParseException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			
		Incidencia inci= publicService.setIncidencia(problema,categoria,gender);
		model.addAttribute("results",inci);
		model.addAttribute("token", token.getToken());
		return "confirmacion";
	}

	@RequestMapping("/nuevousuario")
	public String altaUsuario(Model model,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		return "nuevousuario";
	}
	
	@RequestMapping("/nuevaincidencia")
	public String altaIncidencia(Model model,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		return "nuevaincidencia";
	}
	
	@RequestMapping("/listaincidencias")
	public String listaIncidencia(Model model) {
		
		return "listaincidencias";
	}
	
	@RequestMapping("/Volver")
	public String volver(Model model,Usuario usuario,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		if(request.isUserInRole("ADMIN") || request.isUserInRole("TECH") || request.isUserInRole("USER")) {
			if(request.isUserInRole("ADMIN")) {
				model.addAttribute("admin","Rol administrador");
				 
				
				model.addAttribute("token", token.getToken());
			}else if (request.isUserInRole("TECH")) {
				model.addAttribute("tech","Rol técnico");
				model.addAttribute("token", token.getToken());
			}else {
				model.addAttribute("user","Rol usuario");
				model.addAttribute("token", token.getToken());
			}
			return "/inipage";
		}else
			{
			return "/";
			}
	}
	
	@GetMapping("/listaincidenciasadmin")
	public String listaIncidenciaAdmin(Model model) {
		List<Incidencia> incidencias=publicService.getIncidencias();
		model.addAttribute("results",incidencias);
		
		return "listaincidencias";
	}
	
	@GetMapping("/listaincidenciaspendientes")
	public String listaIncidenciasPendientes(Model model) {
		List<Incidencia> incidencias=publicService.getIncidenciasPendientes();
		model.addAttribute("results",incidencias);
		model.addAttribute("lista", "pendiente");
		Usuario user=publicService.getLogUser();
		return "listaincidencias";
	}
	
	@GetMapping("/listaincidenciasporUsuario")
	public String listaIncidenciasporUsuario(Model model) {
		Usuario user=publicService.getLogUser();
		List<Incidencia> incidencias=publicService.getIncidenciasByUser(user.getId());
		model.addAttribute("results",incidencias);
		model.addAttribute("lista", "Totales");
		return "listaincidencias";
	}
	
	@GetMapping("/listaincidenciastratadas")
	public String listaIncidenciasTratadas(Model model) {
		Usuario user=publicService.getLogUser();
		List<Usuario> tecnicos=new ArrayList<>();
		tecnicos.add(user);
		List<Incidencia> incidencias=publicService.getIncidenciasByTech(tecnicos);
		model.addAttribute("results",incidencias);
		model.addAttribute("lista", "tratadas");
		return "listaincidencias";
	}
	
	@RequestMapping("/confirmacion")
	public String confirmacion(Model model,@RequestParam Incidencia incidencia) {
		
		Incidencia incidencia2=publicService.getIncidencia(incidencia);
		model.addAttribute("results",incidencia2);

		return "confirmacion";
	}
	
}
