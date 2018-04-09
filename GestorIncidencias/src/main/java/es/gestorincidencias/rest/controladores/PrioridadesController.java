package es.gestorincidencias.rest.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.PrioridadIncidencia;
import es.gestorincidencias.repositorios.CategoriaRepository;
import es.gestorincidencias.repositorios.PrioridadRepositorio;

@RestController
@RequestMapping(value="/v1/prioridades")
public class PrioridadesController {
	@Autowired
	private PrioridadRepositorio prioridadRepo;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<PrioridadIncidencia> getCategorias(){
		return prioridadRepo.findAll();
	}
}
