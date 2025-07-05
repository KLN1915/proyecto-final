package spring.projects.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.projects.final_project.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

}