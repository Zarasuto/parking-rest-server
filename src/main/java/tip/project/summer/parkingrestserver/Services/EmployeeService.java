package tip.project.summer.parkingrestserver.Services;

import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeService {
    Employee loadByUsername(String username);
    void saveEmployeeToDatabase(Employee employee, String authority);
    ArrayList<EmployeeDTO> getAllEmployees();
}
