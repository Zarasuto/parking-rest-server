package tip.project.summer.parkingrestserver.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.EmployeeDTO;
import tip.project.summer.parkingrestserver.Services.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeOperationsController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("api/admin/GetAllEmployees")
    private List<EmployeeDTO> getAllEmployee(){
        ArrayList<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        return employeeDTOS;
    }
}
