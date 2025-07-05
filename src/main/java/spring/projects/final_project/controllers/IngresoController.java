package spring.projects.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.projects.final_project.model.Ingreso;
import spring.projects.final_project.repository.IngresoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class IngresoController {
    @Autowired
    private IngresoRepository ingresoRepository;

    @GetMapping("/ingresos")
    public List<Ingreso> listarIngresos(){
        return ingresoRepository.findAll();
    }

    @PostMapping("/ingresos")
    public Ingreso guardarIngreso(@RequestBody Ingreso ingreso){
        return ingresoRepository.save(ingreso);
    }
}
