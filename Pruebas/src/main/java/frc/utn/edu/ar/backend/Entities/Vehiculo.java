package frc.utn.edu.ar.backend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PATENTE")
    private String patente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MODELO")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Prueba> pruebas;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Posicion> posiciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return id == vehiculo.id && Objects.equals(patente, vehiculo.patente) && Objects.equals(modelo, vehiculo.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patente, modelo);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                '}';
    }

    public void addPrueba(Prueba prueba) {
        this.pruebas.add(prueba);
    }
}
