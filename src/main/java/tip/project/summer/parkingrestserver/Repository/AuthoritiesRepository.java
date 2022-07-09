package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities,Long> {
}
