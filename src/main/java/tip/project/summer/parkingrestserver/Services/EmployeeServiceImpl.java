package tip.project.summer.parkingrestserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.Roles;
import tip.project.summer.parkingrestserver.Repository.EmployeeRepository;
import tip.project.summer.parkingrestserver.Repository.RolesRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RolesRepository rolesRepository;


    @Override
    public Employee loadByUsername(String username) {
        return employeeRepository.findOneByUsernameAndEnabledTrue(username);
    }

    @Override
    public void saveEmployeeToDatabase(Employee employee, String role) {
        employeeRepository.save(employee);
        rolesRepository.save(new Roles(role,employee));

    }
}
