package tip.project.summer.parkingrestserver.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.Employee;
import tip.project.summer.parkingrestserver.Model.EmployeeDto;
import tip.project.summer.parkingrestserver.Services.EmployeeServiceImpl;

@RestController
public class LoginAndRegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("user/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setUsername(employeeDto.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employeeService.saveEmployeeToDatabase(employee, employeeDto.getRole());
        return new ResponseEntity<String>("Registration Successful",HttpStatus.OK);
    }
}
