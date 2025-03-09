package frc.utn.edu.ar.backend.DTO;

import frc.utn.edu.ar.backend.Entities.Posicion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PosicionDTO {
    private int id;
    private LocalDateTime fechaHora;
    private double latitud;
    private double longitud;
    private VehiculoDTO vehiculo;


}
