package es.gestorincidencias.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PrioridadIncidencia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String prioridad;
	
	@OneToMany(mappedBy="prioridad")
	@JsonIgnore
	private List<Incidencia> incidencias;
	
	public PrioridadIncidencia() {};
	
	public PrioridadIncidencia(String prioridad) {
		super();
		this.prioridad = prioridad;
	}
	
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	
}
