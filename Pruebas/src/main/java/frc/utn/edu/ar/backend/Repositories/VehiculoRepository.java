package frc.utn.edu.ar.backend.Repositories;

import frc.utn.edu.ar.backend.Entities.Posicion;
import frc.utn.edu.ar.backend.Entities.Vehiculo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
}
