package frc.utn.edu.ar.backend.ServicioExterno.Concretas;

import  java.util.*;

public class RespuestaAPI {
     private Coordenadas coordenadasAgencia;
     private int radioAdmitidoKm;
     private List<ZonaRestringida> zonasRestringidas;

     // Getters y setters
     public Coordenadas getCoordenadasAgencia() {
         return coordenadasAgencia;
     }
     public void setCoordenadasAgencia(Coordenadas coordenadasAgencia) {
         this.coordenadasAgencia = coordenadasAgencia;
     }
     public int getRadioAdmitidoKm() {
         return radioAdmitidoKm;
     }
     public void setRadioAdmitidoKm(int radioAdmitidoKm) {
         this.radioAdmitidoKm = radioAdmitidoKm;
     }
     public List<ZonaRestringida> getZonasRestringidas() {
         return zonasRestringidas;
     }
     public void setZonasRestringidas(List<ZonaRestringida> zonasRestringidas) {
         this.zonasRestringidas = zonasRestringidas;
     }

}
