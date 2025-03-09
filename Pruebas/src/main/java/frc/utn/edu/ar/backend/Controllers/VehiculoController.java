package frc.utn.edu.ar.backend.Controllers;

import frc.utn.edu.ar.backend.Entities.Vehiculo;
import frc.utn.edu.ar.backend.Services.PruebaService;
import frc.utn.edu.ar.backend.Services.VehiculoService;
import frc.utn.edu.ar.backend.ServicioExterno.Concretas.Notificacion;
import frc.utn.edu.ar.backend.ServicioExterno.Concretas.RespuestaAPI;
import frc.utn.edu.ar.backend.ServicioExterno.NotificacionesClient2;
import frc.utn.edu.ar.backend.ServicioExterno.ServicoExterno2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {
    private final VehiculoService service;
    private final ServicoExterno2 servicoExterno2;
    private final NotificacionesClient2 notificacionesClient2;
    private final PruebaService pruebaService;
    

    public VehiculoController(VehiculoService service, ServicoExterno2 servicoExterno2, NotificacionesClient2 notificacionesClient2, PruebaService pruebaService) {
        this.service = service;
        this.servicoExterno2 = servicoExterno2;
        this.notificacionesClient2 = notificacionesClient2;
        this.pruebaService = pruebaService;
    }

    // Validar si el vehiculo esta en los limites enviados por parametro (punto 2)
    @GetMapping("/limites/{id}")
    public ResponseEntity<String> validarVehiculoEnLimite(@PathVariable int id, @RequestParam(value = "Latitud", defaultValue = "0") float latitud, @RequestParam(value = "Longitud", defaultValue = "0") float longitud) {

        Vehiculo vehiculo = service.buscarPorId(id);

        // Hace los get del servicio provisto  por la catedra
        int radioAdmitido = Objects.requireNonNull(servicoExterno2.obtenerDatos().getBody()).getRadioAdmitidoKm();
        RespuestaAPI apiExt = servicoExterno2.obtenerDatos().getBody();

        // Verifica si el vehiculo tiene una prueba en curso
        if (service.tienePruebaEnCurso(vehiculo)) {
            assert apiExt != null;

            // Verifica si el vehiculo esta fuera de los limites
            if (Math.abs(latitud - apiExt.getCoordenadasAgencia().getLat()) > radioAdmitido || Math.abs(longitud - apiExt.getCoordenadasAgencia().getLon()) > radioAdmitido) {
                Notificacion notificacion = new Notificacion();
                notificacion.setDescripcion("Vehiculo " + vehiculo.getPatente() + " fuera de los limites");
                notificacionesClient2.crearNotificacion(notificacion); // Llama al post del mmicroservicio de notificaciones

                pruebaService.setearIncidentePrueba(id); // Setea el incidente en la prueba y marca al interesado

                return ResponseEntity.ok("Vehiculo fuera de los limites");

            // Verifica si el vehiculo esta en una zona restringida
            } else if (apiExt.getZonasRestringidas().stream().anyMatch(zonaRestringida -> zonaRestringida.estaEnZonaRestringida(latitud, longitud))) {
                Notificacion notificacion = new Notificacion();
                notificacion.setDescripcion("Vehiculo " + vehiculo.getPatente() + " en zona restringida");
                notificacionesClient2.crearNotificacion(notificacion); // Llama al post del mmicroservicio de notificaciones

                pruebaService.setearIncidentePrueba(id); // Setea el incidente en la prueba y marca al interesado

                return ResponseEntity.ok("Vehiculo en zona restringida");
            } else {
                return ResponseEntity.ok("Vehiculo dentro de las coordenadas esperadas");
            }
        } else {
            return ResponseEntity.ok("El vehiculo no tiene pruebas en curso");
        }
    }
}
