package tip.project.summer.parkingrestserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Model.Timestamps;
import tip.project.summer.parkingrestserver.Model.User;
import tip.project.summer.parkingrestserver.Model.UserDTO;
import tip.project.summer.parkingrestserver.Repository.TimestampRepository;
import tip.project.summer.parkingrestserver.Repository.UserRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private TimestampRepository timestampRepository;

    @Autowired
    private UserRepository userRepository;

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addUserToDatabase(User user, String timestamp) throws IllegalArgumentException{
        userRepository.save(user);
        timestampRepository.save(new Timestamps(parseTimestamp(timestamp),user));
    }

    @Override
    public ArrayList<UserDTO> getAllParkedUsers() {
        ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userList= userRepository.findAllByParkingslotIsNotNull();
        for(User user:userList){
            UserDTO userDTO = new UserDTO();
            userDTO.setUid(user.getUid());
            userDTO.setContactNum(user.getContactnum());
            userDTO.setParking_slot(user.getParkingslot());
            userDTO.setPlateNum(user.getPlatenum());
            userDTO.setTimeStamp(user.getTimestamps().iterator().next().getTimestamp().toString());
            userDTOArrayList.add(userDTO);
        }
        return userDTOArrayList;
    }

    private Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
