package tip.project.summer.parkingrestserver.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tip.project.summer.parkingrestserver.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findOneByUsernameAndEnabledTrue(String username);
}
