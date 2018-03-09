package es.gestorincidencias.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String pass;

	
	/*@OneToMany
	private List<Incidencia> incidencias;*/
	
	@ManyToOne()
	private RolUsuario rol;
	
	public Usuario() {};
	
	
	public Usuario(String nombre, String apellidos, String email, String pass) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.pass = pass;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}


	/*public List<Incidencia> getIncidencias() {
		return incidencias;
	}


	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public void setIncidencias(Incidencia incidencia) {
		if (this.incidencias == null) {
			this.incidencias=new ArrayList<Incidencia>();
		}
		this.incidencias.add(incidencia);
	}*/
	public RolUsuario getRol() {
		return rol;
	}


	public void setRol(RolUsuario rol) {
		this.rol = rol;
	}
	
	
}
