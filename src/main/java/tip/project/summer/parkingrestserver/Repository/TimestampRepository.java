package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.Timestamps;

public interface TimestampRepository extends JpaRepository<Timestamps,Long> {
}
