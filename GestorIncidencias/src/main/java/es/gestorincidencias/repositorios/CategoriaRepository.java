package es.gestorincidencias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gestorincidencias.entidades.CategoriaIncidencia;

public interface CategoriaRepository extends JpaRepository<CategoriaIncidencia, Integer> {

}
