package es.gestorincidencias.servicios;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.EstadoIncidencia;
import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.repositorios.CategoriaRepository;
import es.gestorincidencias.repositorios.IncidenciaRepositorio;
import es.gestorincidencias.repositorios.EstadoRepository;



@Service
public class PublicService {
	Date ahora=new Date();
	
	@Autowired
	private IncidenciaRepositorio incidenciaRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private EstadoRepository estadoRepo;

	public List<CategoriaIncidencia> getCategorias(){
		return categoriaRepo.findAll();
	}
	
	/*
	public int getCategoria(String categoria){
		
		int num=getCategoria(categoria);
		return num;
	}*/
	public List<Incidencia> getFaq(String id) {
		int idNum=Integer.parseInt(id);
		CategoriaIncidencia categoria=categoriaRepo.findOne(idNum);
		return incidenciaRepo.findDisctinctByCategoriasAndIsFaq(categoria,true);
	}

	public Incidencia getIncidencia(String id) {
		long idNum=Long.parseLong(id);
		return incidenciaRepo.findOne(idNum);
	}
	public Incidencia getIncidencia(Incidencia incidencia) {
		long idNum=incidencia.getId();
		return incidenciaRepo.findOne(idNum);
	}
	
	public Incidencia setIncidencia(String problema,boolean esfaq) {
	//IncidenciaCategorias inciCat=new IncidenciaCategorias();	
	//int num=getCategoria(categoria);
	//CategoriaIncidencia catinci=categoriaRepo.findOne(num);
	EstadoIncidencia estado=estadoRepo.findOne(0);
	Incidencia inci = new Incidencia();
	//inci.setCategorias(catinci);
	inci.setEstado(estado);
	inci.setProblema(problema);
	inci.setFechaInicio(ahora);
	inci.setFaq(esfaq);
	incidenciaRepo.save(inci);
		
	return inci;
		
	}

	public List<Incidencia> getFaqBySearch(String search) {
		return incidenciaRepo.findLikeProblemaAndIsFaq(search,true);
	}
	

}
