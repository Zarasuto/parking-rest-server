package tip.project.summer.parkingrestserver.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.EmployeeRegistrationDTO;
import tip.project.summer.parkingrestserver.Services.EmployeeServiceImpl;

@RestController
public class LoginAndRegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("api/admin/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRegistrationDTO employeeRegistrationDTO){
        Employee employee = new Employee();
        employee.setUsername(employeeRegistrationDTO.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeRegistrationDTO.getPassword()));
        employeeService.saveEmployeeToDatabase(employee, employeeRegistrationDTO.getRole());
        return new ResponseEntity<String>("Registration Successful",HttpStatus.OK);
    }
}
