package es.gestorincidencias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestorincidencias.entidades.PrioridadIncidencia;

public interface PrioridadRepositorio extends JpaRepository<PrioridadIncidencia, Integer> {

}
