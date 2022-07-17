package tip.project.summer.parkingrestserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tip.project.summer.parkingrestserver.Model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllByParkingslotIsNotNull();
    User findByUid(String uid);

    @Transactional
    @Modifying
    @Query(value="update User u set u.parkingslot=null where u.id=:id")
    void updateParkingSlot(@Param(value="id") long id);
}
