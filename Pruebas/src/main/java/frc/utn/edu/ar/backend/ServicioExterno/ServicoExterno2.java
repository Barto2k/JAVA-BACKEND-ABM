package frc.utn.edu.ar.backend.ServicioExterno;

import frc.utn.edu.ar.backend.ServicioExterno.Concretas.RespuestaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoExterno2 {
    private final RestTemplate restTemplate;

    private static final String URL = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";

    public ServicoExterno2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<RespuestaAPI> obtenerDatos() {
        return restTemplate.getForEntity(URL, RespuestaAPI.class);
    }
}
