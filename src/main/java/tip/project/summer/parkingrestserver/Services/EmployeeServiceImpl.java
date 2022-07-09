package tip.project.summer.parkingrestserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.Authorities;
import tip.project.summer.parkingrestserver.Repository.EmployeeRepository;
import tip.project.summer.parkingrestserver.Repository.AuthoritiesRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;


    @Override
    public Employee loadByUsername(String username) {
        return employeeRepository.findOneByUsernameAndEnabledTrue(username);
    }

    @Override
    public void saveEmployeeToDatabase(Employee employee, String authority) {
        employeeRepository.save(employee);
        authoritiesRepository.save(new Authorities(authority,employee.getId()));

    }
}
