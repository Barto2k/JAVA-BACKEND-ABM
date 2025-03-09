package frc.utn.edu.ar.backend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Posiciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHA_HORA")
    private LocalDateTime fechaHora;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private double longitud;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return id == posicion.id && Double.compare(latitud, posicion.latitud) == 0 && Double.compare(longitud, posicion.longitud) == 0 && Objects.equals(fechaHora, posicion.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaHora, latitud, longitud);
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", fechaHora=" + fechaHora +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
