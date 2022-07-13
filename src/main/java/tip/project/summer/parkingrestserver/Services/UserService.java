package tip.project.summer.parkingrestserver.Services;

import tip.project.summer.parkingrestserver.Model.User;
import tip.project.summer.parkingrestserver.Model.UserDTO;

import java.util.ArrayList;

public interface UserService {

    void addUserToDatabase(User user, String timestamp);
    ArrayList<UserDTO> getAllParkedUsers();
    UserDTO getUserInfo(String uid);
}
