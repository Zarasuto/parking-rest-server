package tip.project.summer.parkingrestserver.AdminController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.EmployeeDTO;
import tip.project.summer.parkingrestserver.Model.EmployeeRegistrationDTO;
import tip.project.summer.parkingrestserver.Services.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeOperationsController {

    Logger logger = LoggerFactory.getLogger(EmployeeOperationsController.class);

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("api/admin/GetAllEmployees")
    private List<EmployeeDTO> getAllEmployee(){
        ArrayList<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
        return employeeDTOS;
    }

    @PostMapping("api/admin/DeleteEmployee")
    private ResponseEntity<String> deleteEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            employeeService.deleteEmployee(employeeDTO.getUsername());
            return new ResponseEntity<String>("Deletion Succesful", HttpStatus.OK);
        }catch (IllegalArgumentException | NullPointerException e){
            logger.error(e.getMessage());
            return new ResponseEntity<String>("Deletion Failed",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("api/admin/ChangePassword")
    private ResponseEntity<String> changePassword(@RequestBody EmployeeRegistrationDTO employeeDTO){
        try{
            employeeService.changePassword(employeeDTO.getUsername(),passwordEncoder.encode(employeeDTO.getPassword()));
            return new ResponseEntity<String>("Update Succesful", HttpStatus.OK);
        }catch(IllegalArgumentException | NullPointerException e){
            logger.error(e.getMessage());
            return new ResponseEntity<String>("Update Failed",HttpStatus.BAD_REQUEST);
        }
    }
}
