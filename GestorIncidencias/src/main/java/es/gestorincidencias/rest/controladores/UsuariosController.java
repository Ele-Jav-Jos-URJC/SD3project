package es.gestorincidencias.rest.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.gestorincidencias.entidades.Usuario;
import es.gestorincidencias.servicios.PublicService;

@RestController
@RequestMapping(value="v1/usuarios")
public class UsuariosController {
	@Autowired
	private PublicService pService;
	
	@PostMapping(value="/adduser")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario addUser(@RequestBody Usuario usuario){
		return pService.addUsuario(usuario);
		
		
	}
}
