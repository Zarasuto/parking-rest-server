package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.Roles;

public interface RolesRepository extends JpaRepository<Roles,Long> {
}
