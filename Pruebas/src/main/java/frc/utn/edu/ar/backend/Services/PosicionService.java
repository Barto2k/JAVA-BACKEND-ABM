package frc.utn.edu.ar.backend.Services;

import frc.utn.edu.ar.backend.DTO.PosicionDTO;
import frc.utn.edu.ar.backend.Entities.Posicion;
import frc.utn.edu.ar.backend.Repositories.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PosicionService {
    private final PosicionRepository repository;

    @Autowired
    public PosicionService(PosicionRepository repository) {this.repository = repository;}
    public Posicion create(Posicion posicion) {return repository.save(posicion);}

    // Método auxiliar para convertir Posicion a PosicionDTO
    PosicionDTO convertirADTO(Posicion posicion) {
        if (posicion == null) return null;
        PosicionDTO dto = new PosicionDTO();
        dto.setId(posicion.getId());
        dto.setFechaHora(posicion.getFechaHora());
        dto.setLatitud(posicion.getLatitud());
        dto.setLongitud(posicion.getLongitud());
        return dto;
    }
}
