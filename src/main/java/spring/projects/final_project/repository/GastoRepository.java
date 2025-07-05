package spring.projects.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.projects.final_project.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

}