package es.restService.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.restService.entidades.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	Usuario findById(Long id);
	
}
