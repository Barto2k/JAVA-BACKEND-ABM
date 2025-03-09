package frc.utn.edu.ar.backend.Reportes;

import frc.utn.edu.ar.backend.DTO.PruebaDTO;
import frc.utn.edu.ar.backend.Entities.Posicion;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Entities.Vehiculo;
import frc.utn.edu.ar.backend.Services.PruebaService;
import frc.utn.edu.ar.backend.Services.VehiculoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class Reportes {
    private final VehiculoService vehiculoService;
    private final PruebaService pruebaService;

    public Reportes(VehiculoService vehiculoService, PruebaService pruebaService) {
        this.vehiculoService = vehiculoService;
        this.pruebaService = pruebaService;
    }

    // Calcula la cantidad de km recorridos por un vehiculo en pruebas
    public Double obtenerKmRecorridos(int id, LocalDateTime fechaDesde, LocalDateTime fechaHasta){
        Vehiculo vehiculo = vehiculoService.buscarPorId(id);
        List<Posicion> posiciones = vehiculo.getPosiciones();

        // Filtra las posiciones por fecha y las ordena
        List<Posicion> posicionesOrdenadas = posiciones.stream()
                .filter(posicion -> posicion.getFechaHora().isAfter(fechaDesde)  && posicion.getFechaHora().isBefore(fechaHasta))
                .sorted(Comparator.comparing(Posicion::getFechaHora))
                .toList();

        // Inicializa los acumuladores
        double acuLatitud = 0.0;
        double acuLongitud = 0.0;

        // Calcula la cantidad de km recorridos
        for (int i = 1; i < posicionesOrdenadas.size(); i++) {
            Double latitudAnterior = posicionesOrdenadas.get(i - 1).getLatitud();
            Double longitudAnterior = posicionesOrdenadas.get(i - 1).getLongitud();

            Double latitudActual = posicionesOrdenadas.get(i).getLatitud();
            Double longitudActual = posicionesOrdenadas.get(i).getLongitud();

            acuLatitud += Math.abs(latitudActual - latitudAnterior);
            acuLongitud += Math.abs(longitudActual - longitudAnterior);
        }

        return (acuLatitud + acuLongitud);
    }

    // Busca todas las pruebas del vehiculo
    public List<String> detallePrueba(int id){
        return vehiculoService.buscarPruebasPorVehiculo(id);
    }

    // Busca todas las pruebas con incidentes
    public List<PruebaDTO> pruebasIncidentes(){
        List<Prueba> pruebas = pruebaService.obtenerPruebasIncidentes();
        return pruebas.stream()
                .map(pruebaService::convertirADTO)
                .toList();
    }

    // Busca todas las pruebas con incidentes de un empleado
    public List<PruebaDTO> incidentesEmpleado(int id){
        List<Prueba> pruebas = pruebaService.obtenerPruebasIncidentesEmpleado(id);
        return pruebas.stream()
                .map(pruebaService::convertirADTO)
                .toList();
    }
}
