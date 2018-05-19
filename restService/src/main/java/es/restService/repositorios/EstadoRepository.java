package es.restService.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.restService.entidades.EstadoIncidencia;

public interface EstadoRepository extends JpaRepository<EstadoIncidencia, Integer> {

}
