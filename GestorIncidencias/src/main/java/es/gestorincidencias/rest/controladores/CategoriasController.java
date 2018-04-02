package es.gestorincidencias.rest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.repositorios.CategoriaRepository;

@RestController
@RequestMapping(value="/v1/categorias")
public class CategoriasController {
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<CategoriaIncidencia> getCategorias(){
		return categoriaRepo.findAll();
	}
}
