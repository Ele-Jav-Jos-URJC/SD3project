package es.gestorincidencias.repositorios;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.EstadoIncidencia;
import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.entidades.Usuario;

@CacheConfig (cacheNames="incidencia")
public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

	@Cacheable
	public List<Incidencia> findDisctinctByCategoriaAndIsFaq(CategoriaIncidencia categoria, boolean isFaq);
	@Cacheable
	public List<Incidencia> findDisctinctByCategoria(CategoriaIncidencia categoria);
	@Cacheable
	public List<Incidencia> findDistinctByUsuario(Usuario user);
	@Cacheable
	public List<Incidencia>findDisctinctByIsFaq(boolean isFaq);
	@Cacheable
	public List<Incidencia> findByEstado(EstadoIncidencia estado);
	@Cacheable
	public List<Incidencia> findDisctinctByTecnicos(List<Usuario> tecnicos);
	@Cacheable
	@Query("Select i from Incidencia as i where i.problema like %?1% and i.isFaq=?2")
	public List<Incidencia> findLikeProblemaAndIsFaq(String problema, boolean isFaq);
	
}
