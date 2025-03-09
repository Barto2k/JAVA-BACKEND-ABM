package frc.utn.edu.ar.backend.ServicioExterno;

import frc.utn.edu.ar.backend.ServicioExterno.Concretas.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacionesClient2 {

//    @Autowired
//    public NotificacionesClient2(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ResponseEntity<Notificacion> crearNotificacion(Notificacion notificacion) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//
//        HttpEntity<Notificacion> requestEntity = new HttpEntity<>(notificacion, headers);
//
//        return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, Notificacion.class);
//    }
    public void crearNotificacion(Notificacion p) {
    // Creación de una instancia de RestTemplate
        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            // Creación de la entidad a enviar
            HttpEntity<Notificacion> entity = new HttpEntity<>(p);
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo Persona.
            ResponseEntity<Notificacion> res = template.postForEntity(
                    "http://localhost:3000/api/notificacion/nueva", entity, Notificacion.class
            );
        } catch (HttpClientErrorException ex) {
            System.out.println("Error al enviar la notificación");
        }
    }
}
