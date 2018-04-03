package es.gestorincidencias.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CategoriaIncidencia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String categoria;
	
	@OneToMany(mappedBy="categoria")
	@JsonIgnore
	private List<Incidencia> incidencias;
	
	public CategoriaIncidencia() {}

	
	
	public CategoriaIncidencia(String categoria) {
		super();
		this.categoria = categoria;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}
	
	/*public int getCategoria(String categoria) {
		int num=this.id;
		return num;
		
	}*/

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public List<Incidencia> getIncidencias() {
		return incidencias;
	}



	public void setIncidecncias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	
}
