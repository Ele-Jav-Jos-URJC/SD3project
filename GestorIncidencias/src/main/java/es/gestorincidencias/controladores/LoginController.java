package es.gestorincidencias.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import es.gestorincidencias.seguridad.*;
import org.springframework.security.web.csrf.CsrfToken;

import es.gestorincidencias.repositorios.UsuarioRepository;
/**
 * controlador para a auntetificación de usuarios
 * @author Javier Aparicio
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@RequestMapping("/login")
	public String getLogin(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		return "logeo";
	}
	
	@RequestMapping("/loginerror")
	public String getLoginError(Model model, HttpServletRequest request) {
		 
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());
		return "loginerror";
	}
	
	@GetMapping("/inipage")
	public String getloginini(Model model, HttpServletRequest request) {
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("token", token.getToken());
		
		if(request.isUserInRole("ADMIN")) {
			model.addAttribute("admin","Rol administrador");
		}else if (request.isUserInRole("TECH")) {
			model.addAttribute("tech","Rol técnico");
		}else {
			model.addAttribute("user","Rol usuario");
		}
		
		return "inipage";
	}
	
	
}
