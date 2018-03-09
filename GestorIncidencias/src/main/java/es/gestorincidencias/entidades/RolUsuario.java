package es.gestorincidencias.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RolUsuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String rol;
	
	@OneToMany(mappedBy="rol")
	private List<Usuario> usuarios;

	public RolUsuario() {}
	public RolUsuario(String rol) {
		super();
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
