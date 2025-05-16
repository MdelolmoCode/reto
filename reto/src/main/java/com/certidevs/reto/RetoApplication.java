package com.certidevs.reto;

import com.certidevs.reto.entities.Proyecto;
import com.certidevs.reto.entities.Tarea;
import com.certidevs.reto.repositories.ProyectoRepository;
import com.certidevs.reto.repositories.TareaRepository;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class RetoApplication {

	public static void main(String[] args) {

		// inicializar spring
		ApplicationContext spring = SpringApplication.run(RetoApplication.class, args);

		// obtener los repositorios
		ProyectoRepository proyectoRepository = spring.getBean(ProyectoRepository.class);
		TareaRepository tareaRepository = spring.getBean(TareaRepository.class);

		// crear proyectos
		Proyecto proyecto1 = new Proyecto("Web App", "Aplicación web",
				LocalDate.of(2025, 1, 15), true);
		Proyecto proyecto2 = new Proyecto("Mobile App", "Aplicación para Android",
				LocalDate.of(2025, 3, 1), false);

		// guardar proyectos
		proyectoRepository.save(proyecto1);
		proyectoRepository.save(proyecto2);

		// crear tareas
		Tarea tarea1 = new Tarea("Diseño de la interfaz", "Diseñar la interfaz de usuario",
				true, proyecto1);
		Tarea tarea2 = new Tarea("Implementación backend", "Implementar lógica de backend", false, proyecto1);
		Tarea tarea3 = new Tarea("Configurar base de datos", "descripción 3", true, proyecto2);
		Tarea tarea4 = new Tarea("Escribir documentación", "descripción 4", false, proyecto1);
		Tarea tarea5 = new Tarea("Pruebas unitarias", "descripción 5", true, proyecto2);

		// guardar tareas
		tareaRepository.saveAll(List.of(tarea1, tarea2, tarea3, tarea4, tarea5));

		// probar métodos y consultas

		// de proyectoRepository
		// probar Proyecto findByNombre(String nombre)
		String nombreProyectoBuscado =  "Web App";
		Proyecto proyectoEncontrado = proyectoRepository.findByNombre(nombreProyectoBuscado);
		if (proyectoEncontrado != null) {
			System.out.println("Proyecto encontrado por nombre: '" + nombreProyectoBuscado +
					"': " + proyectoEncontrado.getNombre());
		} else {
			System.out.println("No se encontró ningún proyecto con el nombre: " + nombreProyectoBuscado);
		}

		// probar List<Proyecto> findByFechaInicio(LocalDate fechaInicio)
		LocalDate fechaBuscada = LocalDate.of(2025, 1, 15);
		List<Proyecto> proyectosPorFecha = proyectoRepository.findByFechaInicio(fechaBuscada);
		System.out.println("Proyectos con fecha de inicio: " + fechaBuscada);
		if (proyectosPorFecha.isEmpty()) {
			System.out.println("No se han encontrado proyectos en la fecha: " + fechaBuscada);
		} else {
			for (Proyecto proyecto : proyectosPorFecha) {
				System.out.println(proyecto.getNombre() + ": " + proyecto.getDescripción());
			}
		}

		// probar List<Proyecto> findByActivoTrue()
		List<Proyecto> proyectosActivos = proyectoRepository.findByActivoTrue();
		if (proyectosActivos.isEmpty()) {
			System.out.println("No se han encontrado proyectos activos");
		} else {
			for (Proyecto proyecto : proyectosActivos) {
				System.out.println(proyecto.getNombre() + ": " + proyecto.getDescripción());
			}
		}

		// probar Tarea findByTitulo(String titulo)
		String tituloBuscado = "Diseño de la interfaz";
		Tarea tareaEncontrada = tareaRepository.findByTitulo(tituloBuscado);
		if (tareaEncontrada != null) {
			System.out.println("Tarea encontrada por título '" + tituloBuscado +
					"': " + tareaEncontrada.getTitulo());
		}

		// probar long countByCompletadaFalse()
		long tareasNoCompletadas = tareaRepository.countByCompletadaFalse();
		System.out.println("Tareas a completar: " + tareasNoCompletadas);

		// probar List<Tarea> findByProyecto_Id(Long id)
		Long proyectoId = proyecto2.getId();
		List<Tarea> tareasProyecto = tareaRepository.findByProyecto_Id(proyectoId);
		if (tareasProyecto.isEmpty()) {
			System.out.println("El proyecto con id " + proyectoId + " no tiene tareas.");
		} else {
			System.out.println("Tareas del proyecto con id " + proyectoId + ":");
			for (Tarea tarea : tareasProyecto) {
				System.out.println(tarea.getTitulo() + ": " +
						(tarea.getCompletada() ? "Completada" : "No completada"));
			}
		}

	}

}
