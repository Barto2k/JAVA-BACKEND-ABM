package frc.utn.edu.ar.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Empleados")
@Data

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEGAJO")
    private int legajo;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private int telefonoContacto;

    @OneToMany(mappedBy = "empleado", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Prueba> pruebas;

    public void addPrueba(Prueba prueba) {
        pruebas.add(prueba);
    }
}
