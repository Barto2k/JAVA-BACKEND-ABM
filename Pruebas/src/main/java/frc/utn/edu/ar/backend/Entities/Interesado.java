package frc.utn.edu.ar.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Interesados")
@Data
@NoArgsConstructor
public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "RESTRINGIDO")
    private boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private int nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private LocalDateTime fechaVencimientoLicencia;

    @OneToMany(mappedBy = "interesado", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Prueba> pruebas;


    public Interesado(String nombre, String apellido, String tipoDocumento, String documento, String nroLicencia, String fechaVencimientoLicencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nroLicencia = Integer.parseInt(nroLicencia);
        this.fechaVencimientoLicencia = LocalDateTime.parse(fechaVencimientoLicencia);
    }

    public void addPrueba(Prueba prueba) {
        this.pruebas.add(prueba);
    }
}
