package frc.utn.edu.ar.backend.Repositories;

import frc.utn.edu.ar.backend.Entities.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
}
