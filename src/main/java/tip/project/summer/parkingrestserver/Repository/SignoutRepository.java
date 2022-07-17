package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.SignOut;

public interface SignoutRepository extends JpaRepository<SignOut,Long> {
}
