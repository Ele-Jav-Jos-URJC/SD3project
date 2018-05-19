package es.restService.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.restService.entidades.PrioridadIncidencia;

public interface PrioridadRepositorio extends JpaRepository<PrioridadIncidencia, Integer> {

}
