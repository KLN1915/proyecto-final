package spring.projects.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.projects.final_project.exception.ResourceNotFoundException;
import spring.projects.final_project.model.Presupuesto;
import spring.projects.final_project.repository.PresupuestoRepository;
import spring.projects.final_project.repository.IngresoRepository;

import spring.projects.final_project.dto.PresupuestoResponseDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1")
public class PresupuestoController {
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

//    @GetMapping("/presupuestos")
//    public List<Presupuesto> listarPresupuestos(){
//        return presupuestoRepository.findAll();
//    }
    @GetMapping("/presupuestos")
    public List<PresupuestoResponseDTO> listarPresupuestos() {
        Float totalIngresos = ingresoRepository.sumAllMontos(); // necesita método custom
        List<Presupuesto> presupuestos = presupuestoRepository.findAll();

        return presupuestos.stream().map(p -> {
            Float valorCalculado = (p.getPorcentaje() / 100f) * totalIngresos;
            return new PresupuestoResponseDTO(
                    p.getId(),
                    p.getNombre(),
                    p.getPorcentaje(),
                    valorCalculado
            );
        }).collect(Collectors.toList());
    }

    @PostMapping("/presupuestos")
    public Presupuesto guardarPresupuesto(@RequestBody Presupuesto presupuesto){
        return presupuestoRepository.save(presupuesto);
    }

    @GetMapping("/presupuestos/{id}")
    public ResponseEntity<Presupuesto> listarPresupuestoPorId(@PathVariable Long id){
        Presupuesto presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El presupuesto con id: " + id + "no existe"));
        return ResponseEntity.ok(presupuesto);
    }

    @PutMapping("/presupuestos/{id}")
    public ResponseEntity<Presupuesto> actualizarPresupuesto(@PathVariable Long id, @RequestBody Presupuesto presupuestoRequest){
        Presupuesto presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El presupuesto con id: " + id + "no existe"));
        presupuesto.setNombre(presupuestoRequest.getNombre());
        presupuesto.setPorcentaje(presupuestoRequest.getPorcentaje());

        Presupuesto presupuestoActualizado = presupuestoRepository.save(presupuesto);

        return ResponseEntity.ok(presupuestoActualizado);
    }

    @DeleteMapping("/presupuestos/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarPresupuesto(@PathVariable Long id){
        Presupuesto presupuesto = presupuestoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El presupuesto con id: " + id + "no existe"));
        presupuestoRepository.delete(presupuesto);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
