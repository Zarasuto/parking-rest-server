package tip.project.summer.parkingrestserver.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tip.project.summer.parkingrestserver.Model.Employee;

import javax.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findOneByUsernameAndEnabledTrue(String username);

    @Transactional
    @Modifying
    @Query(value="update Employee u set u.password=:password where u.id=:id")
    void updatePassword(@Param(value="id") long id, @Param(value="password") String password);
}
