package spring.projects.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.projects.final_project.model.Gasto;
import spring.projects.final_project.repository.GastoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GastoController {

    @Autowired
    private GastoRepository gastoRepository;


    @GetMapping("/gastos")
    public List<Gasto> listarGastos() {
        return gastoRepository.findAll();
    }


    @PostMapping("/gastos")
    public Gasto guardarGasto(@RequestBody Gasto gasto) {
        return gastoRepository.save(gasto);
    }


    @GetMapping("/gastos/total")
    public Float obtenerTotalGastos() {
        return gastoRepository.totalGastos();
    }


    @GetMapping("/gastos/total/presupuesto/{id}")
    public Float obtenerTotalPorPresupuesto(@PathVariable Long id) {
        return gastoRepository.totalPorPresupuesto(id);
    }
}