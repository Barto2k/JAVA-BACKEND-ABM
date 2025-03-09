package frc.utn.edu.ar.backend.Services;

import frc.utn.edu.ar.backend.DTO.InteresadoDTO;
import frc.utn.edu.ar.backend.Entities.Interesado;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Repositories.InteresadoRepository;
import frc.utn.edu.ar.backend.Repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresadoService {
    private final InteresadoRepository repository;

    @Autowired
    public InteresadoService(InteresadoRepository repository) {this.repository = repository;}

    public Interesado create(Interesado interesado) {
        return repository.save(interesado);
    }

    public Interesado buscarPorId(int id) {
        return repository.findById(id).orElseThrow();
    }

    public void agregarPrueba(Interesado interesado, Prueba prueba){
        interesado.getPruebas().add(prueba);
    }

    public InteresadoDTO convertirADTO(Interesado interesado) {
        if (interesado == null) return null;
        InteresadoDTO dto = new InteresadoDTO();
        dto.setId(interesado.getId());
        dto.setNombre(interesado.getNombre());
        dto.setApellido(interesado.getApellido());
        dto.setTipoDocumento(interesado.getTipoDocumento());
        dto.setDocumento(interesado.getDocumento());
        dto.setRestringido(interesado.isRestringido());
        dto.setNroLicencia(interesado.getNroLicencia());
        dto.setFechaVencimientoLicencia(interesado.getFechaVencimientoLicencia());
        return dto;
    }
}
