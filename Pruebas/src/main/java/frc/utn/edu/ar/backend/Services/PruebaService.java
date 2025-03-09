package frc.utn.edu.ar.backend.Services;


import frc.utn.edu.ar.backend.DTO.EmpleadoDTO;
import frc.utn.edu.ar.backend.DTO.InteresadoDTO;
import frc.utn.edu.ar.backend.DTO.PruebaDTO;
import frc.utn.edu.ar.backend.DTO.VehiculoDTO;
import frc.utn.edu.ar.backend.Entities.Empleado;
import frc.utn.edu.ar.backend.Entities.Interesado;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Entities.Vehiculo;
import frc.utn.edu.ar.backend.Repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PruebaService {

    private final PruebaRepository repository;
    private final InteresadoService interesadoService;
    private final VehiculoService vehiculoService;
    private final EmpleadoService empleadoService;

    @Autowired
    public PruebaService(PruebaRepository repository, InteresadoService interesadoService, VehiculoService vehiculoService, EmpleadoService empleadoService) {
        this.repository = repository;
        this.interesadoService = interesadoService;
        this.vehiculoService = vehiculoService;
        this.empleadoService = empleadoService;
    }

    // Crear una nueva prueba validando lo correspondiente
    public Prueba create(Prueba prueba, int idVehiculo, int idInteresado, int idEmpleado) throws Exception {

        // Buscar el vehículo, interesado y empleado por sus IDs
        Interesado interesado = interesadoService.buscarPorId(idInteresado);
        Empleado empleado = empleadoService.buscarPorId(idEmpleado);
        Vehiculo vehiculo = vehiculoService.buscarPorId(idVehiculo);

        // Validar que la licencia del interesado no esté vencida
        if (interesado.getFechaVencimientoLicencia().isBefore(LocalDateTime.now())) {
            throw new Exception("La licencia del interesado está vencida.");
        }

        // Validar que el interesado no esté restringido para probar vehículos
        if (interesado.isRestringido()) {
            throw new Exception("El interesado tiene restricciones para probar vehículos.");
        }

        // Validar que el vehículo no esté siendo probado en este momento
        boolean isVehiculoEnPrueba = repository.existsByVehiculoAndFechaHoraFinIsNull(vehiculo);
        if (isVehiculoEnPrueba) {
            throw new Exception("El vehículo ya está siendo probado actualmente.");
        }

        // Setear los atributos de la prueba
        prueba.setFechaHoraInicio(LocalDateTime.now());
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setVehiculo(vehiculo);

        // Agregar la prueba a las listas de vehículo, interesado y empleado
        interesadoService.agregarPrueba(interesado, prueba);
        empleadoService.agregarPrueba(empleado, prueba);
        vehiculoService.agregarPrueba(vehiculo, prueba);

        // Si todas las validaciones pasan, guardar la prueba
        return repository.save(prueba);
    }

    // Buscar todas las pruebas en curso
    public List<Prueba> buscarPruebasEnCurso() {
        return repository.findAllByFechaHoraFinIsNull();
    }

    // Setear un incidente en una prueba y en el interesado de dicha prueba
    public void setearIncidentePrueba(int id) {
        Vehiculo vehiculo = vehiculoService.buscarPorId(id);
        Prueba prueba = repository.findPruebaByVehiculoAndFechaHoraFinIsNull(vehiculo);
        prueba.setIncidentes(true);
        prueba.getInteresado().setRestringido(true);
        repository.save(prueba);
    }

    // Finalizar una prueba permitiendo agregar un comentario
    public void finalizarPrueba(int pruebaId, String comentario) throws Exception {

        // Buscar la prueba por su ID
        Prueba prueba = repository.findById(pruebaId).orElseThrow();

        // Actualizar la fecha de finalización y el comentario
        prueba.setFechaHoraFin(LocalDateTime.now());
        prueba.setComentarios(comentario);

        repository.save(prueba);
    }

    // Obtener pruebas con incidentes
    public List<Prueba> obtenerPruebasIncidentes() {
        return repository.findAllByIncidentesTrue();
    }

    // Obtener pruebas con incidentes de un empleado
    public List<Prueba> obtenerPruebasIncidentesEmpleado(int id) {
        Empleado empleado = empleadoService.buscarPorId(id);
        return repository.findAllByEmpleadoAndIncidentesTrue(empleado);
    }

    // Convertir una Prueba a PruebaDTO
    public PruebaDTO convertirADTO(Prueba prueba) {
        PruebaDTO dto = new PruebaDTO();
        dto.setId(prueba.getId());
        dto.setFechaHoraInicio(prueba.getFechaHoraInicio());
        dto.setFechaHoraFin(prueba.getFechaHoraFin());
        dto.setComentarios(prueba.getComentarios());

        VehiculoDTO vehiculoDTO = vehiculoService.convertirADTO(prueba.getVehiculo());
        dto.setVehiculo(vehiculoDTO);

        InteresadoDTO interesadoDTO = interesadoService.convertirADTO(prueba.getInteresado());
        dto.setInteresado(interesadoDTO);

        EmpleadoDTO empleadoDTO = empleadoService.convertirADTO(prueba.getEmpleado());
        dto.setEmpleado(empleadoDTO);
        return dto;
    }
}
