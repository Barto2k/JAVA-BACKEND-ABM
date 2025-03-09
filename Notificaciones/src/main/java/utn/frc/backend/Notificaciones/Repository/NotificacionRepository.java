package utn.frc.backend.Notificaciones.Repository;

import org.springframework.data.repository.CrudRepository;
import utn.frc.backend.Notificaciones.Entidad.Notificacion;

public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {
}
