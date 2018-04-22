package es.gestorincidencias.repositorios;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.gestorincidencias.entidades.CategoriaIncidencia;


@CacheConfig (cacheNames="incidencia")
public interface CategoriaRepository extends JpaRepository<CategoriaIncidencia, Integer> {
	
	@Cacheable
	public CategoriaIncidencia findDistinctByCategoria(String categoria);
}
