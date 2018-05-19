package es.restService.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.restService.bd.BDService;
import es.restService.entidades.CategoriaIncidencia;


@RestController
@RequestMapping(value="/v1/categorias")
public class CategoriasController {

	@Autowired
	private BDService pService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<CategoriaIncidencia> getCategorias(){
		return pService.getCategorias();
	}
}
