package es.gestorincidencias.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EstadoIncidencia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String estado;
	private String correo;
	
	/*@OneToMany
	private List<Incidencia> incidencias;*/
	
	public EstadoIncidencia() {}
	
	public EstadoIncidencia(String estado, String correo) {
		super();
		this.estado = estado;
		this.correo=correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/*public List<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}*/
	
	
	
}
