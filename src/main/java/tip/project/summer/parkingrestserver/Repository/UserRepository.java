package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllByParkingslotIsNotNull();
}
