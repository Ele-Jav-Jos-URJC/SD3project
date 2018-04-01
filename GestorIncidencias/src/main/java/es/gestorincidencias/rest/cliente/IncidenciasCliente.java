package es.gestorincidencias.rest.cliente;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.gestorincidencias.entidades.Incidencia;
@Component
public class IncidenciasCliente {
 private RestTemplate restTemplate;
 
public IncidenciasCliente() {
	super();
	this.restTemplate = new RestTemplate();
    
	
}
 
 public List<Incidencia> getLista(String url){
	 List<Incidencia> resp=new ArrayList<>();
	 Incidencia[] data=restTemplate.getForObject(url, Incidencia[].class);
	 if (data!=null) {
		 for (Incidencia i:data) {
			 resp.add(i);
		 } 
	 }else {
		 resp=null;
	 }
	 
	 return resp;
 }
 
}
