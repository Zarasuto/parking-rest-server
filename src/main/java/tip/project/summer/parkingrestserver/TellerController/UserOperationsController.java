package tip.project.summer.parkingrestserver.TellerController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tip.project.summer.parkingrestserver.Model.*;
import tip.project.summer.parkingrestserver.Services.UserServiceImpl;

import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.Map;

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
        }catch(NonUniqueResultException ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>("UID already Created",HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("api/teller/signout")
    public ResponseEntity<String> signout(@RequestBody SignOutDTO signOutDTO){
        try{
            userService.SignoutUser(signOutDTO.getUid(),signOutDTO.getTimestamp());
            return new ResponseEntity<>("Signout successful", HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>("Signout failed", HttpStatus.BAD_REQUEST);
        }catch(NonUniqueResultException ex){
            logger.warn(ex.getMessage());
            return new ResponseEntity<>("Already signed out", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("api/teller/signin")
    public ResponseEntity<String> signin(@RequestBody SigninDTO signinDTO){
        try{
            userService.SigninUser(signinDTO.getUid(),signinDTO.getTimestamp(),signinDTO.getParkingslot());
            return new ResponseEntity<>("Signin successful", HttpStatus.OK);
        }catch(IllegalArgumentException ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>("Signin failed", HttpStatus.BAD_REQUEST);
        }catch(NonUniqueResultException ex){
            logger.warn(ex.getMessage());
            return new ResponseEntity<>("Already signed in", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("api/teller/getparkedusers")
    public ArrayList<UserDTO> getParkedUsers(){
        ArrayList<UserDTO> userDTOArrayList = userService.getAllParkedUsers();
        return userDTOArrayList;
    }

    @PostMapping("api/teller/getuserinfo")
    public UserDTO getUserInfo(@RequestBody Map<String,String> UID){
        try{
            return userService.getUserInfo(UID.get("uid"));
        }catch(IllegalArgumentException ex){
            logger.warn(ex.getMessage());
            return null;
        }catch(IncorrectResultSizeDataAccessException ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    @GetMapping("api/teller/getallusers")
    public ArrayList<UserDTOWithTimestampList> getAllUsers(){
        return userService.getAllUsers();
    }
}
