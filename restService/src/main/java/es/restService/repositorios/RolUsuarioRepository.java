package es.restService.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.restService.entidades.RolUsuario;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {

	RolUsuario findByrol(String rol);

}
