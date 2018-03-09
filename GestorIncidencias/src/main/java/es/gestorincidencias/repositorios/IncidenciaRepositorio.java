package es.gestorincidencias.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.Incidencia;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

	public List<Incidencia> findDisctinctByCategoriasAndIsFaq(CategoriaIncidencia categoria, boolean isFaq);
	public List<Incidencia>findDisctinctByIsFaq(boolean isFaq);
	@Query("Select i from Incidencia as i where i.problema like %?1% and i.isFaq=?2")
	public List<Incidencia> findLikeProblemaAndIsFaq(String problema, boolean isFaq);
	
}
