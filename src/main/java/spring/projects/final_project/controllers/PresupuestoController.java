package spring.projects.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.projects.final_project.model.Presupuesto;
import spring.projects.final_project.repository.PresupuestoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PresupuestoController {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @GetMapping("/presupuestos")
    public List<Presupuesto> listarPresupuestos(){
        return presupuestoRepository.findAll();
    }

    @PostMapping("/presupuestos")
    public Presupuesto guardarPresupuesto(@RequestBody Presupuesto presupuesto){
        return presupuestoRepository.save(presupuesto);
    }
}
