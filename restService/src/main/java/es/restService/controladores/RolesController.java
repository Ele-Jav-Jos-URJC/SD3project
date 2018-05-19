package es.restService.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.restService.bd.BDService;
import es.restService.entidades.RolUsuario;

import es.restService.repositorios.RolUsuarioRepository;

@RestController
@RequestMapping(value="/v1/roles")
public class RolesController {
	@Autowired
	private RolUsuarioRepository rolRepo;
	@Autowired
	private BDService pService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<RolUsuario> getCategorias(){
		//return rolRepo.findAll();
		return pService.getAllRol();
	}
}
