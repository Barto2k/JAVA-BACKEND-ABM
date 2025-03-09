package frc.utn.edu.ar.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpleadoDTO {
    private int legajo;
    private String nombre;
    private String apellido;
    private int telefonoContacto;
    private List<PruebaDTO> pruebas;
}
