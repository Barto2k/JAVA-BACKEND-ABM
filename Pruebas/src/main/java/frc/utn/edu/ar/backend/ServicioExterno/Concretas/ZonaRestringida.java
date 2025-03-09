package frc.utn.edu.ar.backend.ServicioExterno.Concretas;

public class ZonaRestringida {
    private Coordenadas noroeste;
    private Coordenadas sureste;

    // Getters y setters
    public Coordenadas getNoroeste() {
        return noroeste;
    }
    public void setNoroeste(Coordenadas noroeste) {
        this.noroeste = noroeste;
    }
    public Coordenadas getSureste() {
        return sureste;
    }
    public void setSureste(Coordenadas sureste) {
        this.sureste = sureste;
    }

    public boolean estaEnZonaRestringida(float latitud, float longitud){
        return (sureste.getLat() <= latitud && latitud <= noroeste.getLat()) && (noroeste.getLon() <= longitud && sureste.getLon() >= longitud);
    }
}
