package tip.project.summer.parkingrestserver.Services;

import tip.project.summer.parkingrestserver.Model.Employee;

public interface EmployeeService {
    public Employee loadByUsername(String username);
    public void saveEmployeeToDatabase(Employee employee, String authority);
}
