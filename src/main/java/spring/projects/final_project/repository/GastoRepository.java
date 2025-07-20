package spring.projects.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.projects.final_project.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    // 🔢 Suma total de todos los gastos
    @Query("SELECT COALESCE(SUM(g.monto), 0) FROM Gasto g")
    Float totalGastos();

    // 💰 Suma total de gastos por ID de presupuesto
    @Query("SELECT COALESCE(SUM(g.monto), 0) FROM Gasto g WHERE g.presupuesto.id = :presupuestoId")
    Float totalPorPresupuesto(@Param("presupuestoId") Long presupuestoId);
}