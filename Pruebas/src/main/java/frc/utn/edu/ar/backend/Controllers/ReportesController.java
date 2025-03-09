package frc.utn.edu.ar.backend.Controllers;

import frc.utn.edu.ar.backend.DTO.PruebaDTO;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Reportes.Reportes;
import frc.utn.edu.ar.backend.Services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    private final Reportes service;

    @Autowired
    public ReportesController(Reportes service) {
        this.service = service;
    }

    // Reporte de km recorridos por un vehiculo en pruebas
    @GetMapping("/km/{id}")
    public ResponseEntity<Double> reporteCantKmPruebas(@PathVariable int id, @RequestParam(value = "FechaDesde")LocalDateTime fechaDesde, @RequestParam(value = "FechaHasta")LocalDateTime fechaHasta) {
        if (fechaDesde == null) {
            fechaDesde = LocalDateTime.now();
        } else if (fechaHasta == null) {
            fechaHasta = LocalDateTime.now();
        }
        return ResponseEntity.ok(service.obtenerKmRecorridos(id, fechaDesde, fechaHasta));
    }

    // Reporte de pruebas realizadas por un vehiculo
    @GetMapping("/detalle/{id}")
    public ResponseEntity<List<String>> detallePruebas(@PathVariable int id) {
        return ResponseEntity.ok(service.detallePrueba(id));
    }

    // Reporte de pruebas con incidentes
    @GetMapping("/pruebas")
    public ResponseEntity<List<PruebaDTO>> reportePruebas() {
        return ResponseEntity.ok(service.pruebasIncidentes());
    }

    // Reporte de incidentes para un empleado
    @GetMapping("/incidentes/{id}")
    public ResponseEntity<List<PruebaDTO>> reporteIncidentesEmpleado(@PathVariable int id) {
        return ResponseEntity.ok(service.incidentesEmpleado(id));
    }
}
