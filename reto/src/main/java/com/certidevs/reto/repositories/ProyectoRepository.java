package com.certidevs.reto.repositories;

import com.certidevs.reto.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

  // método derivado para buscar proyectos por nombre
  Proyecto findByNombre(String nombre);

  // método derivado para buscar proyectos por fecha de inicio
  List<Proyecto> findByFechaInicio(LocalDate fechaInicio);

  // consulta JPQL que encuentre todos los proyectos activos
  @Query("select p from Proyecto p where p.activo = true")
  List<Proyecto> findByActivoTrue();

}