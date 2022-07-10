package tip.project.summer.parkingrestserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.Authorities;
import tip.project.summer.parkingrestserver.Model.EmployeeDTO;
import tip.project.summer.parkingrestserver.Repository.EmployeeRepository;
import tip.project.summer.parkingrestserver.Repository.AuthoritiesRepository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() {
        ArrayList<EmployeeDTO> employeeDTOList=new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        for(Employee employee:employeeList){
            employeeDTOList.add(new EmployeeDTO(employee.getId(),
                    employee.getAuthorities().iterator().next().getAuthority(),
                    employee.getUsername()));
        }
        return employeeDTOList;
    }

}
