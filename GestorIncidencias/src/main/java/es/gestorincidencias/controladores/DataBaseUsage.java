package es.gestorincidencias.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.EstadoIncidencia;
import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.entidades.PrioridadIncidencia;
import es.gestorincidencias.entidades.RolUsuario;
import es.gestorincidencias.entidades.Usuario;
import es.gestorincidencias.repositorios.CategoriaRepository;
import es.gestorincidencias.repositorios.EstadoRepository;
import es.gestorincidencias.repositorios.IncidenciaRepositorio;
import es.gestorincidencias.repositorios.PrioridadRepositorio;
import es.gestorincidencias.repositorios.RolUsuarioRepository;
import es.gestorincidencias.repositorios.UsuarioRepository;
import scala.annotation.meta.setter;

// prueba descarga2
@Controller
public class DataBaseUsage implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usRepo;
	@Autowired
	private RolUsuarioRepository rolRepo;
	@Autowired
	private IncidenciaRepositorio incidenciaRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private PrioridadRepositorio prioridadRepo;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		//Creo el contenido de la tabla categorías
				CategoriaIncidencia impresora=new CategoriaIncidencia("Impresora");
				CategoriaIncidencia red=new CategoriaIncidencia("Red");
				CategoriaIncidencia telefonia=new CategoriaIncidencia("Telefonía");
				categoriaRepo.save(impresora);
				categoriaRepo.save(red);
				categoriaRepo.save(telefonia);
				
				//creo el contendio de la tabla prioridad de incidncia
				PrioridadIncidencia baja=new PrioridadIncidencia("Baja");
				PrioridadIncidencia alta=new PrioridadIncidencia("Alta");
				PrioridadIncidencia critica=new PrioridadIncidencia("Crítica");
				prioridadRepo.save(baja);
				prioridadRepo.save(alta);
				prioridadRepo.save(critica);
				
				//creo el contenido de la tabla rol
				RolUsuario general=new RolUsuario("ROLE_USER");
				RolUsuario tecnico= new RolUsuario("ROLE_TECH");
				RolUsuario admin=new RolUsuario("ROLE_ADMIN");
				rolRepo.save(general);
				rolRepo.save(tecnico);
				rolRepo.save(admin);
				
				//creo el contenido de la tabla estado
				EstadoIncidencia pendiente=new EstadoIncidencia("Pendiente", "mensaje pendiente");
				EstadoIncidencia enCurso=new EstadoIncidencia("En curso", "Estado en curso");
				EstadoIncidencia cerrada =new EstadoIncidencia("Cerrada", "mensaje cerrado");
				EstadoIncidencia reclamacion =new EstadoIncidencia("Reclamado", "mensaje reclamación");
				estadoRepo.save(pendiente);
				estadoRepo.save(enCurso);
				estadoRepo.save(cerrada);
				estadoRepo.save(reclamacion);
				
				
				
				//creo tres usuarios, uno por cada tipo
				Usuario us1=new Usuario("nombre1", "apellidos1", "email1@mail.com", "pass");
				us1.setRol(general);
				
				Usuario us2=new Usuario("nombre2", "apellidos2", "email2@mail.com", "pass2");
				us2.setRol(tecnico);
				
				Usuario us3=new Usuario("nombre3", "apellidos3", "email3@mail.com", "pass3");
				us3.setRol(admin);
				usRepo.save(us1);
				usRepo.save(us2);
				usRepo.save(us3);
				
				Date ahora=new Date();
				//SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
				//el usuario1 crea cuatro incidencias
				Incidencia inc1=new Incidencia(us1,ahora,"problema1",baja,pendiente," ");
				Incidencia inc2=new Incidencia(us1,ahora,"problema2",baja,pendiente," ");
				Incidencia inc3=new Incidencia(us1,ahora,"problema3",alta,pendiente," ");
				Incidencia inc4=new Incidencia(us1,ahora,"problema4",baja, pendiente," ");
				inc1.setCategoria(red);
				incidenciaRepo.save(inc1);
				incidenciaRepo.save(inc2);
				incidenciaRepo.save(inc3);
				incidenciaRepo.save(inc4);
				
				List<Usuario> tecnicos=new ArrayList<>();
				//la incidencia 2 y 3 son recogidas por el técnico
				tecnicos.add(us2);
				inc2.setTecnicos(tecnicos);
				inc2.setEstado(enCurso);
				inc2.setCategoria(impresora);
				inc3.setTecnicos(tecnicos);
				inc3.setEstado(enCurso);
				inc3.setCategoria(red);
				incidenciaRepo.save(inc2);
				incidenciaRepo.save(inc3);
				
				//la incidencia 4 es recogida por el admin
				tecnicos.clear();
				tecnicos.add(us3);
				inc4.setTecnicos(tecnicos);
				inc4.setEstado(enCurso);
				inc4.setCategoria(telefonia);
				incidenciaRepo.save(inc4);
				
				//la incidencia 3 es cerrada
				//incluye la solución
				inc3.setInforme("he solucionado la incidencia 3");
				//incluye la fecha
				inc1.setFechaCierre(new Date());
				inc2.setFechaCierre(new Date());
				inc3.setFechaCierre(new Date());
				inc4.setFechaCierre(new Date());
				//cierro la incidencia
				inc3.setEstado(cerrada);
				//la marco como faq para que se muestre en publico
				inc3.setFaq(true);
				incidenciaRepo.save(inc3);
		
	}

}
