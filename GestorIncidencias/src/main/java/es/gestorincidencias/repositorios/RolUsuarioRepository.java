package es.gestorincidencias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestorincidencias.entidades.RolUsuario;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {

}
