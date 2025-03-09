package frc.utn.edu.ar.backend.Services;

import frc.utn.edu.ar.backend.DTO.VehiculoDTO;
import frc.utn.edu.ar.backend.Entities.Posicion;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Entities.Vehiculo;
import frc.utn.edu.ar.backend.Repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehiculoService {
    private final VehiculoRepository repository;
    private final PosicionService posicionService;

    @Autowired
    public VehiculoService(VehiculoRepository repository, PosicionService posicionService) {
        this.repository = repository;
        this.posicionService = posicionService;
    }

    public Vehiculo create(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    public Vehiculo buscarPorId(int id) {
        return repository.findById(id).orElseThrow();
    }

    public void agregarPrueba(Vehiculo vehiculo, Prueba prueba){
        vehiculo.getPruebas().add(prueba);
    }

    public boolean tienePruebaEnCurso(Vehiculo vehiculo) {
        return vehiculo.getPruebas().stream()
                .anyMatch(prueba -> prueba.getFechaHoraFin() == null);
    }

    public Posicion obtenerUltimaPosicion(Vehiculo vehiculo) {
        return vehiculo.getPosiciones().stream().min(Comparator.comparing(Posicion::getFechaHora)) // Esto devuelve un Optional<Posicion>
                .orElse(null); // Devuelve null si no hay posiciones
    }

    public List<String> buscarPruebasPorVehiculo(int id) {
        Vehiculo vehiculo = buscarPorId(id);
        return vehiculo.getPruebas().stream()
                .map(Prueba::toString)
                .toList();
    }

    // Convertir Vehiculo a VehiculoDTO
    public VehiculoDTO convertirADTO(Vehiculo vehiculo) {
        if (vehiculo == null) return null; // Verifica que el objeto no sea null antes de convertir
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(vehiculo.getId());
        dto.setPatente(vehiculo.getPatente());
        dto.setPosiciones(vehiculo.getPosiciones().stream()
                .map(posicionService::convertirADTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
