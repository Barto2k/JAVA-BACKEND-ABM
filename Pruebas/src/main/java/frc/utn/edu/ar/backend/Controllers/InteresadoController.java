package frc.utn.edu.ar.backend.Controllers;

import frc.utn.edu.ar.backend.DTO.InteresadoDTO;
import frc.utn.edu.ar.backend.Entities.Interesado;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Services.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/interesado")
public class InteresadoController {
    private final InteresadoService service;

    @Autowired
    public InteresadoController(InteresadoService service) {
        this.service = service;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<InteresadoDTO> crearInteresado(@RequestBody Interesado interesado) {
        Interesado creado = service.create(interesado);
        InteresadoDTO dto = service.convertirADTO(creado);
        return ResponseEntity.ok(dto);
    }

}
