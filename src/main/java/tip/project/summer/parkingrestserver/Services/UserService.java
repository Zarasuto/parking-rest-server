package tip.project.summer.parkingrestserver.Services;

import tip.project.summer.parkingrestserver.Model.User;

public interface UserService {

    void addUserToDatabase(User user, String timestamp);
}
