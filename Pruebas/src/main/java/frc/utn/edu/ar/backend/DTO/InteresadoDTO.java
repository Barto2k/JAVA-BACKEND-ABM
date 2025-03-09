package frc.utn.edu.ar.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InteresadoDTO {
        private Integer id;
        private String nombre;
        private String apellido;
        private String tipoDocumento;
        private String documento;
        private boolean restringido;
        private int nroLicencia;
        private LocalDateTime fechaVencimientoLicencia;
        private List<PruebaDTO> pruebas;
}
