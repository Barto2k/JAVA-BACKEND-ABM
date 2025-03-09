package frc.utn.edu.ar.backend.Controllers;

import frc.utn.edu.ar.backend.DTO.*;
import frc.utn.edu.ar.backend.Entities.*;
import frc.utn.edu.ar.backend.Services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {
    private final PruebaService service;

    @Autowired
    public PruebaController(PruebaService service) {
        this.service = service;
    }

    @PostMapping("/nueva")
    public ResponseEntity<PruebaDTO> crearPrueba(@RequestParam(value = "idVehiculo", defaultValue = "1") int idVehiculo, @RequestParam(value = "idInteresado", defaultValue = "1") int idInteresado, @RequestParam(value = "idEmpleado", defaultValue = "1") int idEmpleado) throws Exception {
        Prueba nuevo = new Prueba();
        Prueba creada = service.create(nuevo, idVehiculo, idInteresado, idEmpleado);
        PruebaDTO dto = service.convertirADTO(creada);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PruebaDTO>> mostrarPruebas() {
        List<Prueba> pruebas = service.buscarPruebasEnCurso();
        List<PruebaDTO> dtos = pruebas.stream()
                .map(service::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizarPrueba(@PathVariable int id, @RequestParam(value = "comentario", defaultValue = "sin comentario") String comentario) throws Exception {
        service.finalizarPrueba(id, comentario);
    }

}
