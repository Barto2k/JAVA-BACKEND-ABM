package frc.utn.edu.ar.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehiculoDTO {
    private int id;
    private String patente;
    private List<PosicionDTO> posiciones;
}
