package es.gestorincidencias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestorincidencias.entidades.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
}
