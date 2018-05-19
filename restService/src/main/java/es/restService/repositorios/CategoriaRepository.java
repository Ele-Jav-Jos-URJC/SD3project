package es.restService.repositorios;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import es.restService.entidades.CategoriaIncidencia;


@CacheConfig (cacheNames="Incidencia")
public interface CategoriaRepository extends JpaRepository<CategoriaIncidencia, Integer> {
	
	@Cacheable
	public CategoriaIncidencia findDistinctByCategoria(String categoria);
}
