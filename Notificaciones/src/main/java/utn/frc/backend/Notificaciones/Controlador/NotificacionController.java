package utn.frc.backend.Notificaciones.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.Notificaciones.Entidad.Notificacion;
import utn.frc.backend.Notificaciones.Repository.NotificacionRepository;

@RestController
@RequestMapping("/api/notificacion")
public class NotificacionController {

    private final NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionController(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Notificacion>> obtenerNotificaciones() {
        Iterable<Notificacion> notificaciones = notificacionRepository.findAll();
        return ResponseEntity.ok(notificaciones);
    }

    @PostMapping("/nueva")
    public void crearNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionRepository.save(notificacion);
    }
}
