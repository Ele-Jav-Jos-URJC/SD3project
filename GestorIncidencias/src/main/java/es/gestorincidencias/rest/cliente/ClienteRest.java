package es.gestorincidencias.rest.cliente;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.entidades.PrioridadIncidencia;
import es.gestorincidencias.entidades.Usuario;
@Component
public class ClienteRest {
 private RestTemplate restTemplate;
 
public ClienteRest() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
	super();
	TrustStrategy acceptingTrusStrategy=(X509Certificate[] chain, String authType)->true;
	
	SSLContext sslContext=org.apache.http.ssl.SSLContexts.custom()
			.loadTrustMaterial(null, acceptingTrusStrategy).build();
	
	SSLConnectionSocketFactory csf=new SSLConnectionSocketFactory(sslContext);
	
	CloseableHttpClient httpClient= HttpClients.custom()
			.setSSLSocketFactory(csf).build();
	
	HttpComponentsClientHttpRequestFactory requestFactory
		=new HttpComponentsClientHttpRequestFactory();
	requestFactory.setHttpClient(httpClient);
	this.restTemplate = new RestTemplate(requestFactory);
    
	
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
 
 public Incidencia getIncidencia(String url){
	
	 Incidencia resp=restTemplate.getForObject(url, Incidencia.class);
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
 
 public List<PrioridadIncidencia> getListaPrioridad(String url){
	 List<PrioridadIncidencia> resp=new ArrayList<>();
	 PrioridadIncidencia[] data=restTemplate.getForObject(url, PrioridadIncidencia[].class);
	 if (data!=null) {
		 for (PrioridadIncidencia p:data) {
			 resp.add(p);
		 } 
	 }else {
		 resp=null;
	 }
	 
	 return resp;
 }
 
 public Usuario addUsuario(String url,Usuario usuario) {
	 return restTemplate.postForObject(url, usuario, Usuario.class);
 }
}
