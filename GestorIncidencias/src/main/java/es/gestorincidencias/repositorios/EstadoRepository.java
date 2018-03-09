package es.gestorincidencias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestorincidencias.entidades.EstadoIncidencia;

public interface EstadoRepository extends JpaRepository<EstadoIncidencia, Integer> {

}
