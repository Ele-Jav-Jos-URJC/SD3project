package es.gestorincidencias.rest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.RolUsuario;
import es.gestorincidencias.repositorios.CategoriaRepository;
import es.gestorincidencias.repositorios.RolUsuarioRepository;

@RestController
@RequestMapping(value="/v1/roles")
public class RolesController {
	@Autowired
	private RolUsuarioRepository rolRepo;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<RolUsuario> getCategorias(){
		return rolRepo.findAll();
	}
}
