package spring.projects.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresupuestoResponseDTO {
    private Long id;
    private String nombre;
    private Float porcentaje;
    private Float valorCalculado; // porcentaje * totalIngresos
}
