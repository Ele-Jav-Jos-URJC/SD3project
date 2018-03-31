package es.gestorincidencias.servicios;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.gestorincidencias.entidades.*;
import es.gestorincidencias.repositorios.*;




@Service
public class PublicService {
	Date ahora=new Date();
	
	@Autowired
	private IncidenciaRepositorio incidenciaRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private PrioridadRepositorio prioridadRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private RolUsuarioRepository rolusuarioRepo;


	

	public List<CategoriaIncidencia> getCategorias(){
		return categoriaRepo.findAll();
	}
	
	
	public int getCategoria(String categoria){
		
		int num=getCategoria(categoria);
		return num;
	}
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
	
	// Graba un usuario
	public Usuario setUsuario (String nombre,String apellido,String mail,String pass,String rol) 
	{
		Usuario usuario=new Usuario(nombre,apellido,mail,pass);
		RolUsuario rolusuario= new RolUsuario(rol);
		rolusuario=rolusuarioRepo.findByrol(rol);
		usuario.setRol(rolusuario);
		usuarioRepo.save(usuario);
		return usuario;
		
	}
	
	public Incidencia setIncidencia(String problema,String categoria,boolean esfaq) {
	
	//int num=getCategoria(categoria);
	//CategoriaIncidencia catinci=categoriaRepo.findOne(num);
	Usuario usuario=usuarioRepo.findByEmail("email1@mail.com");
	Date fecha=new Date();	
	EstadoIncidencia estado=estadoRepo.findOne(1);
	PrioridadIncidencia prioridad=prioridadRepo.findOne(1);
	//CategoriaIncidencia cat= new CategoriaIncidencia();
	//categoriaRepo.findByCategoria(categoria);
	//categoriaRepo.save(cat);
	Incidencia inci = new Incidencia();
	//inci.setCategorias(cat);
	inci.setPrioridad(prioridad);
	inci.setEstado(estado);
	inci.setProblema(problema);
	inci.setFaq(esfaq);
	inci.setFechaInicio(fecha);
	inci.setUsuario(usuario);
	incidenciaRepo.save(inci);


		
	return inci;
		
	}

	public List<Incidencia> getFaqBySearch(String search) {
		return incidenciaRepo.findLikeProblemaAndIsFaq(search,true);
	}
	

}