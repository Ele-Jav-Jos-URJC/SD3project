package es.gestorincidencias.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Incidencia {
	public interface Principal{}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView(Principal.class)
	private long id;
	@JsonView(Principal.class)
	private Date fechaInicio;
	@JsonView(Principal.class)
	private String problema;
	@JsonView(Principal.class)
	private String informe;
	@JsonView(Principal.class)
	private Date fechaCierre;
	@JsonView(Principal.class)
	private boolean isFaq;
	
	@ManyToOne
	@JsonView(Principal.class)
	private Usuario usuario;
	
	@ManyToMany
	private List<Usuario> tecnicos;
	
	@ManyToOne
	private PrioridadIncidencia prioridad;
	
	@JsonView(Principal.class)
	@ManyToOne
	private EstadoIncidencia estado;
	@ManyToOne
	private CategoriaIncidencia categoria;
	
	public Incidencia() {};
	

	public Incidencia(Usuario usuario,Date fechaInicio, String problema,PrioridadIncidencia prioridad,EstadoIncidencia estado) {
		super();
		
		this.usuario=usuario;
		this.fechaInicio = fechaInicio;
		this.problema = problema;
		this.prioridad=prioridad;
		this.estado = estado;
		this.isFaq=false;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fechaInicio;
	}

	public void setFecha(Date fecha) {
		this.fechaInicio = fecha;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}


	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public boolean isFaq() {
		return isFaq;
	}

	public void setFaq(boolean isFaq) {
		this.isFaq = isFaq;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getTecnicos() {
		return tecnicos;
	}


	public void setTecnicos(List<Usuario> tecnicos) {
		this.tecnicos = tecnicos;
	}

	/*public void setTecnicos(Usuario tecnico) {
		if(this.tecnicos ==null) {
			this.tecnicos=new ArrayList<Usuario>();
		}
		this.tecnicos.add(tecnico);
	}*/

	public PrioridadIncidencia getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(PrioridadIncidencia prioridad) {
		this.prioridad = prioridad;
	}


	public EstadoIncidencia getEstado() {
		return estado;
	}


	public void setEstado(EstadoIncidencia estado) {
		this.estado = estado;
	}


	public CategoriaIncidencia getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaIncidencia categoria) {
		this.categoria = categoria;
	}
	
	
	
}
