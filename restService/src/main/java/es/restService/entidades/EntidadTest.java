package es.restService.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntidadTest {

	@Id
	private int id;
	
	private String valor;

	public EntidadTest() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
