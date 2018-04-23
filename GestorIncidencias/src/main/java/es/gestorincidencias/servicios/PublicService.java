package es.gestorincidencias.servicios;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


	
	/**
	 * Pide a la base de datos todas las categorias
	 * @return List<CategoriaIncidencia>
	 */
	public List<CategoriaIncidencia> getCategorias(){
		return categoriaRepo.findAll();
	}
	/**
	 * Pide a la base de datos la categoria por su id
	 * @return ategoriaIncidencia
	 */
	public CategoriaIncidencia getCategoria(int idCategoria){
		return categoriaRepo.findOne(idCategoria);
	}
	
	public int getCategoria(String categoria){
		
		int num=getCategoria(categoria);
		return num;
	}
	
	
	/**
	 * Pide a la BD un listado de incidencias identificadas como FAQ's peretenecientes a una categoría 
	 * @param id
	 * @return List<Incidencia>
	 */
	public List<Incidencia> getFaqByCategoria(int id) {
		//int idNum=Integer.parseInt(id);
		CategoriaIncidencia categoria=categoriaRepo.findOne(id);
		return incidenciaRepo.findDisctinctByCategoriaAndIsFaq(categoria,true);
	}
	/**
	 * Pide a la BD un listado de incidencias identificadas como FAQ's peretenecientes a una categoría 
	 * @param Nombre categoría
	 * @return List<Incidencia>
	 */
	public List<Incidencia> getFaqByCategoria(String categoria) {
		CategoriaIncidencia categoriaIncidencia=categoriaRepo.findDistinctByCategoria(categoria);
		
		//return categoriaIncidencia.getIncidencias();
		return incidenciaRepo.findDisctinctByCategoriaAndIsFaq(categoriaIncidencia, true);
	}
	
/**
 *  Pide a la BD un listado de incidencias peretenecientes a una categoría
 *  @param categoria
 * @return
 */
	public List<Incidencia> getIncidenciasByCategoria(String categoria) {
		CategoriaIncidencia categoriaIncidencia=categoriaRepo.findDistinctByCategoria(categoria);
		return categoriaIncidencia.getIncidencias();
	}
	
	/*public Incidencia getIncidencia(String id) {
		long idNum=Long.parseLong(id);
		return incidenciaRepo.findOne(idNum);
	}*/
	
	public Incidencia getIncidencia(long id) {
		return incidenciaRepo.findOne(id);
	}
	
	public Incidencia getIncidencia(Incidencia incidencia) {
		long idNum=incidencia.getId();
		return incidenciaRepo.findOne(idNum);
	}

	public List<Incidencia> getIncidenciasByUser(long id){
		Usuario usuario=usuarioRepo.findOne(id);
		//return incidenciaRepo.findDistinctByUsuario( usuario);
		return usuario.getIncidencias();
	}
	
	public Date getIncidenciaFechaCierre(Incidencia incidencia) {
		
		Date fechacierre=incidencia.getFechaCierre();
		return fechacierre;
	}
	
	public Boolean getIncidenciaisFaq(Incidencia incidencia) {
		
		Boolean isfaq=incidencia.isFaq();
		return isfaq;
	}
	/**
	 * Devuelve las incidecias que están llevando un listado de técnicos
	 * @param tecnicos
	 * @return List<Incidencias>
	 */
	public List<Incidencia> getIncidenciasByTech(List<Usuario> tecnicos){
		
		return incidenciaRepo.findDisctinctByTecnicos(tecnicos);
	}
	
	/**
	 * Añade un nuevo usuario a la BD
	 * @author Javier Aparicio
	 * @param user
	 * @return Usuario
	 */
	public Usuario addUsuario (Usuario usuario){
		return usuarioRepo.save(usuario);
				
	}
	
	public List<Incidencia> getIncidenciaByUser(Usuario user){
			
		return incidenciaRepo.findDistinctByUsuario(user); 
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
	
/*	//Devuelve un usuario
	public Usuario getusuario (Long id) {
		Usuario usuario=new Usuario();
		usuarioRepo.findById(id);
		
		return usuario;
	}*/
	
	public Incidencia setIncidencia(String problema,int idCategoria,int idPrioridad,boolean esfaq) throws ParseException {
	
	//int num=getCategoria(categoria);
	//CategoriaIncidencia catinci=categoriaRepo.findOne(num);
	/*Usuario usuario=usuarioRepo.findByEmail("email1@mail.com");
	Date fecha=new Date();	
	Date fechacierre=new Date(01-01-1900);
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
	inci.setInforme("");*/
		Usuario usuario=getLogUser();
		Date fechaInicio=new Date();
		EstadoIncidencia estado=estadoRepo.findOne(1);
		PrioridadIncidencia prioridad=prioridadRepo.findOne(idPrioridad);
		CategoriaIncidencia categoria=categoriaRepo.findOne(idCategoria);
		Incidencia inci=new Incidencia(usuario, fechaInicio, problema, prioridad, estado, "");
		inci.setCategoria(categoria);
/*	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ii:ss");
	String stringFechaConHora = "1900-01-01 00:00:00";
	Date fechaConHora = sdf.parse(stringFechaConHora);
	inci.setFechaCierre(fechaConHora);*/
	//inci.setFechaCierre(fechacierre);
	incidenciaRepo.save(inci);


		
	return inci;
		
	}
	
	/**
	 * @author Javier Aparicio
	 * modifica los campos informe y isfaq de una incidencia en su tratamiento
	 * @param id
	 * @param informe
	 * @param isFaq
	 * @return Incidencia
	 */
	public Incidencia modficarIncidencia(long id,String informe,boolean isFaq) {
		Incidencia resp=incidenciaRepo.findOne(id);
		resp.setFaq(isFaq);
		resp.setInforme(informe);
		incidenciaRepo.save(resp);
		return resp;
	}
	
	// J Cierra una incidencia
	public Incidencia setIncidencia(String informe,Incidencia incidencia,boolean isFaq) {
	EstadoIncidencia estado=estadoRepo.findOne(3);
	PrioridadIncidencia prioridad=prioridadRepo.findOne(1);
	incidencia.setFechaCierre(ahora);
	incidencia.setEstado(estado);
	//incidencia.setUsuario(usuario);
	incidencia.setInforme(informe);
	incidenciaRepo.save(incidencia);
	return incidencia;
		
	}
	/**
	 * Pide a la BD el listado completo de las incidencias
	 * @return List<incidencia>
	 */
	public List<Incidencia> getIncidencias(){
		return incidenciaRepo.findAll();
	}
	/** Pide a la BD el listado de incidencias pendientes
	 * 
	 * @return List<Incidencia>
	 */
	public List<Incidencia> getIncidenciasPendientes(){
		EstadoIncidencia estado=estadoRepo.findOne(1);
		return incidenciaRepo.findByEstado(estado);
	}
	/**
	 * Pide a la BD un listado de incidencias clasificadas como FAQ's que tienen en el campo problema
	 * la cadena pasada en search
	 * @param search
	 * @return List<Incidencia>
	 */
	public List<Incidencia> getFaqBySearch(String search) {
		return incidenciaRepo.findLikeProblemaAndIsFaq(search,true);
	}
	/**
	 * Pide a la BD los diferentes tipos de rioridades
	 * @return List<PrioridadIncidencia>
	 */
	public List<PrioridadIncidencia> getAllPrioridad() {
		return prioridadRepo.findAll();
	}
	/**
	 * devuelve el ususario que está autenticado en ese momento
	 * @return Usuario
	 */
	public Usuario getLogUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName="";
		if (principal instanceof String) {
		  userName = (String) principal;
		}
		
		return usuarioRepo.findByEmail(userName);
	}
	
	public RolUsuario getRol(int rol) {
		return rolusuarioRepo.findOne(rol);
	}
	
	/**Asigna al usuario logeado la incidencia correspondiente
	 * 
	 * @param idIncidencia
	 * @return Incidencia
	 */
	public Incidencia setIncidenciaByTech(long idIncidencia) {
		Incidencia incidencia=incidenciaRepo.getOne(idIncidencia);
		Usuario tecnico=getLogUser();
		incidencia.getTecnicos().add(tecnico);
		incidencia.setEstado(estadoRepo.findOne(2));
		incidenciaRepo.save(incidencia);
		return incidencia;
	}

}