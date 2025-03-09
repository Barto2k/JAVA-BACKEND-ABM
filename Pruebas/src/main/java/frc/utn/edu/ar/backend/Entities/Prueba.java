package frc.utn.edu.ar.backend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Pruebas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHA_HORA_INICIO")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN", nullable = true)
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS", nullable = true)
    private String comentarios;

    @Column(name = "INCIDENTES")
    private boolean incidentes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prueba prueba = (Prueba) o;
        return id == prueba.id && incidentes == prueba.incidentes && Objects.equals(fechaHoraInicio, prueba.fechaHoraInicio) && Objects.equals(fechaHoraFin, prueba.fechaHoraFin) && Objects.equals(comentarios, prueba.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaHoraInicio, fechaHoraFin, comentarios, incidentes);
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "id=" + id +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", comentarios='" + comentarios + '\'' +
                ", incidentes=" + incidentes +
                '}';
    }
}
