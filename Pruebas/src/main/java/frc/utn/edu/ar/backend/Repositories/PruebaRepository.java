package frc.utn.edu.ar.backend.Repositories;

import frc.utn.edu.ar.backend.Entities.Empleado;
import frc.utn.edu.ar.backend.Entities.Prueba;
import frc.utn.edu.ar.backend.Entities.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba, Integer> {
    List<Prueba> findAllByFechaHoraFinIsNull();
    boolean existsByVehiculoAndFechaHoraFinIsNull (Vehiculo vehiculo);
    Prueba findPruebaByVehiculoAndFechaHoraFinIsNull(Vehiculo vehiculo);
    List<Prueba> findAllByIncidentesTrue();
    List<Prueba> findAllByEmpleadoAndIncidentesTrue(Empleado empleado);
}
