package frc.utn.edu.ar.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor

@NoArgsConstructor
@Data
public class PruebaDTO {
    private int id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentarios;
    private VehiculoDTO vehiculo;
    private InteresadoDTO interesado;
    private EmpleadoDTO empleado;
}
