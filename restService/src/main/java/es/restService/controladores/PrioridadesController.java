package es.restService.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.restService.bd.BDService;
import es.restService.entidades.PrioridadIncidencia;


@RestController
@RequestMapping(value="/v1/prioridades")
public class PrioridadesController {
	@Autowired
	private BDService pService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<PrioridadIncidencia> getCategorias(){
		return pService.getAllPrioridad();
	}
}
