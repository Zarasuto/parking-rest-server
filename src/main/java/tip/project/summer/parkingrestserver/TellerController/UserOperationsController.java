package tip.project.summer.parkingrestserver.TellerController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.User;
import tip.project.summer.parkingrestserver.Model.UserDTO;
import tip.project.summer.parkingrestserver.Services.UserServiceImpl;

@RestController
public class UserOperationsController {

    Logger logger = LoggerFactory.getLogger(UserOperationsController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("api/teller/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setUid(userDTO.getUid());
        user.setParkingslot(userDTO.getParking_slot());
        user.setContactnum(userDTO.getContactNum());
        user.setPlatenum(userDTO.getPlateNum());
        try{
            userService.addUserToDatabase(user,userDTO.getTimeStamp());
            return new ResponseEntity<>("User creation successful", HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>("User creation failed", HttpStatus.BAD_REQUEST);
        }
    }
}
