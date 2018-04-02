package es.gestorincidencias.rest.cliente;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.Incidencia;
@Component
public class ClienteRest {
 private RestTemplate restTemplate;
 
public ClienteRest() {
	super();
	this.restTemplate = new RestTemplate();
    
	
}
 
 public List<Incidencia> getListaIncidencias(String url){
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
 
 public List<CategoriaIncidencia> getListaCategoria(String url){
	 List<CategoriaIncidencia> resp=new ArrayList<>();
	 CategoriaIncidencia[] data=restTemplate.getForObject(url, CategoriaIncidencia[].class);
	 if (data!=null) {
		 for (CategoriaIncidencia c:data) {
			 resp.add(c);
		 } 
	 }else {
		 resp=null;
	 }
	 
	 return resp;
 }
}
