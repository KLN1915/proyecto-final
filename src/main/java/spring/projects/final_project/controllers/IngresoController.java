package spring.projects.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.projects.final_project.exception.ResourceNotFoundException;
import spring.projects.final_project.model.Ingreso;
import spring.projects.final_project.repository.IngresoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/ingresos/{id}")
    public ResponseEntity<Ingreso> listarIngresoPorId(@PathVariable Long id){
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El ingreso con id: " + id + "no existe"));
        return ResponseEntity.ok(ingreso);
    }

    @PutMapping("/ingresos/{id}")
    public ResponseEntity<Ingreso> actualizarIngreso(@PathVariable Long id, @RequestBody Ingreso ingresoRequest){
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El ingreso con id: " + id + "no existe"));
        ingreso.setMonto(ingresoRequest.getMonto());
        ingreso.setFecha(ingresoRequest.getFecha());
        ingreso.setConcepto(ingresoRequest.getConcepto());

        Ingreso ingresoActualizado = ingresoRepository.save(ingreso);

        return ResponseEntity.ok(ingresoActualizado);
    }

    @DeleteMapping("/ingresos/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarIngreso(@PathVariable Long id){
        Ingreso ingreso = ingresoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El ingreso con id: " + id + "no existe"));
        ingresoRepository.delete(ingreso);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
