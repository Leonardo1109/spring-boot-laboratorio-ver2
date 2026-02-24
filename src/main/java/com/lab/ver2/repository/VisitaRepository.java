package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer>{
    List<Visita> findByNoCuentaRFCContainingIgnoreCase(String noCuentaRFC);
    List<Visita> findTop5ByOrderByIdDesc(); // cambiar por la paginacion
    List<Visita> findByProyectos_Id(Integer proyectoId);

    /*
    Page<Alumno> buscarPorNombreFlexible(
			@Param("texto") String texto,
			Pageable pageable
    */


    /*
    @Test
	@DisplayName("Paginación básica con ordenamiento")
	void paginacionBasicaTest() {

		System.out.println(ALUMNO);

		Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("nombre"));
		Page<Alumno> page;
		do {
			page = repositorioAlumno.findAll(pageable);

			System.out.println("Total alumnos: " + page.getTotalElements());
			page.forEach(a -> {
				System.out.println(a);
			});
			analizarPagina(page);
			pageable = page.nextPageable();
		} while (page.hasNext());
	}

    private <T> void analizarPagina(Page<T> page) {
            System.out.printf(
                    "Página %d/%d | Elementos: %d | Total: %d%n",
                    page.getNumber() + 1,
                    page.getTotalPages(),
                    page.getNumberOfElements(),
                    page.getTotalElements()
            );
            //page.forEach(System.out::println);
        }

    */
}
