package tip.project.summer.parkingrestserver.Services;

import tip.project.summer.parkingrestserver.Model.User;
import tip.project.summer.parkingrestserver.Model.UserDTO;
import tip.project.summer.parkingrestserver.Model.UserDTOWithTimestampList;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    void addUserToDatabase(User user, String timestamp);
    ArrayList<UserDTO> getAllParkedUsers();
    UserDTO getUserInfo(String uid);
    ArrayList<UserDTOWithTimestampList> getAllUsers();
}
