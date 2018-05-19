package es.restService.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.restService.entidades.Usuario;
import es.restService.bd.BDService;

@RestController
@RequestMapping(value="/v1/usuarios")
public class UsuariosController {
	@Autowired
	private BDService pService;
	
	@PostMapping(value="/adduser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> addUser(@RequestBody Usuario usuario){
		pService.addUsuario(usuario);
		return new ResponseEntity<>(usuario,HttpStatus.CREATED);
		
	}
}
