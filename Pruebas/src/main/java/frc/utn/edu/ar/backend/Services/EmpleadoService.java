package frc.utn.edu.ar.backend.Services;

import frc.utn.edu.ar.backend.DTO.EmpleadoDTO;
import frc.utn.edu.ar.backend.Entities.Empleado;
import frc.utn.edu.ar.backend.Entities.Interesado;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoService {
    private final EmpleadoRepository repository;

    @Autowired
    public EmpleadoService(EmpleadoRepository repository) {this.repository = repository;}
    public Empleado create(Empleado empleado) {return repository.save(empleado);}

    public Empleado buscarPorId(int id) {
        return repository.findById(id).orElseThrow();
    }

    public void agregarPrueba(Empleado empleado, Prueba prueba){
        empleado.getPruebas().add(prueba);
    }

    public EmpleadoDTO convertirADTO(Empleado empleado) {
        if (empleado == null) return null;
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setLegajo(empleado.getLegajo());
        dto.setNombre(empleado.getNombre());
        dto.setApellido(empleado.getApellido());
        dto.setTelefonoContacto(empleado.getTelefonoContacto());
        return dto;
    }
}
