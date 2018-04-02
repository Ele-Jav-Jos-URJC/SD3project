package es.gestorincidencias.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.gestorincidencias.entidades.CategoriaIncidencia;
import es.gestorincidencias.entidades.EstadoIncidencia;
import es.gestorincidencias.entidades.Incidencia;
import es.gestorincidencias.entidades.Usuario;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

	public List<Incidencia> findDisctinctByCategoriaAndIsFaq(CategoriaIncidencia categoria, boolean isFaq);
	public List<Incidencia> findDisctinctByCategoria(CategoriaIncidencia categoria);
	public List<Incidencia> findDistinctByUsuario(Usuario user);
	public List<Incidencia>findDisctinctByIsFaq(boolean isFaq);
	public List<Incidencia> findByEstado(EstadoIncidencia estado);
	public List<Incidencia> findDisctinctByTecnicos(List<Usuario> tecnicos);
	@Query("Select i from Incidencia as i where i.problema like %?1% and i.isFaq=?2")
	public List<Incidencia> findLikeProblemaAndIsFaq(String problema, boolean isFaq);
	
}
