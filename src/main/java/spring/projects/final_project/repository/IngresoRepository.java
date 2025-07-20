package spring.projects.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.projects.final_project.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {

    @Query("SELECT COALESCE(SUM(i.monto), 0) FROM Ingreso i")
    Float sumAllMontos();
}
