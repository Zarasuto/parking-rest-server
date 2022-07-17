package tip.project.summer.parkingrestserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Model.*;
import tip.project.summer.parkingrestserver.Repository.SignoutRepository;
import tip.project.summer.parkingrestserver.Repository.TimestampRepository;
import tip.project.summer.parkingrestserver.Repository.UserRepository;

import javax.persistence.NonUniqueResultException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private TimestampRepository timestampRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignoutRepository signoutRepository;

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addUserToDatabase(User user, String timestamp) throws IllegalArgumentException, NonUniqueResultException{
        try{
            userRepository.findByUid(user.getUid());
        }catch(javax.persistence.NonUniqueResultException| IncorrectResultSizeDataAccessException ex){
            throw new NonUniqueResultException("User Already Created");
        }
        userRepository.save(user);
        timestampRepository.save(new Timestamps(parseTimestamp(timestamp),user));
    }

    @Override
    public ArrayList<UserDTO> getAllParkedUsers() {
        ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userList= userRepository.findAllByParkingslotIsNotNull();
        for(User user:userList){
            try{
                UserDTO userDTO = new UserDTO();
                userDTO.setUid(user.getUid());
                userDTO.setContactNum(user.getContactnum());
                userDTO.setParking_slot(user.getParkingslot());
                userDTO.setPlateNum(user.getPlatenum());
                userDTO.setTimeStamp(user.getTimestamps().iterator().next().getTimestamp().toString());
                userDTOArrayList.add(userDTO);
            }catch (NoSuchElementException ex){
            }
        }
        return userDTOArrayList;
    }

    @Override
    public UserDTO getUserInfo(String uid) throws IllegalArgumentException,IncorrectResultSizeDataAccessException {
        User user = userRepository.findByUid(uid);
        if(user==null){
            throw new IllegalArgumentException("No UID found");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUid());
        userDTO.setContactNum(user.getContactnum());
        userDTO.setParking_slot(user.getParkingslot());
        userDTO.setPlateNum(user.getPlatenum());
        userDTO.setTimeStamp(user.getTimestamps().iterator().next().getTimestamp().toString());
        return userDTO;
    }

    @Override
    public ArrayList<UserDTOWithTimestampList> getAllUsers() {
        ArrayList<UserDTOWithTimestampList> userArrayList = new ArrayList<>();
        for(User user : userRepository.findAll()){
            try{
                UserDTOWithTimestampList userDTOWithTimestampList = new UserDTOWithTimestampList();
                Iterator<Timestamps> timestampsIterator = user.getTimestamps().iterator();
                Iterator<SignOut> signOutIterator = user.getSignouts().iterator();
                while(timestampsIterator.hasNext()){
                    userDTOWithTimestampList.getTimeStamp().add(timestampsIterator.next().getTimestamp().toString());
                }
                while(signOutIterator.hasNext()){
                    userDTOWithTimestampList.getSignouts().add(signOutIterator.next().getTimestamp().toString());
                }
                userDTOWithTimestampList.setContactNum(user.getContactnum());
                userDTOWithTimestampList.setUid(user.getUid());
                userDTOWithTimestampList.setParking_slot(user.getParkingslot());
                userDTOWithTimestampList.setPlateNum(user.getPlatenum());
                userArrayList.add(userDTOWithTimestampList);
            }catch(NoSuchElementException ex){

            }
        }
        return userArrayList;
    }

    @Override
    public void SignoutUser(String uid, String timestamp) throws IllegalArgumentException,NonUniqueResultException{
        User user = userRepository.findByUid(uid);
        if(user==null){
            throw new IllegalArgumentException("No UID Found");
        }else if(user.getParkingslot()==null){
            throw new NonUniqueResultException("User already signed out");
        }
        userRepository.updateParkingSlot(user.getId(),null);
        signoutRepository.save(new SignOut(parseTimestamp(timestamp),user));
    }

    @Override
    public void SigninUser(String uid, String timestamp, String parkingslot) throws IllegalArgumentException,NonUniqueResultException{
        User user = userRepository.findByUid(uid);
        if(user==null){
            throw new IllegalArgumentException("No UID Found");
        }else if(user.getParkingslot()!=null){
            throw new NonUniqueResultException("User already signed in");
        }
        userRepository.updateParkingSlot(user.getId(),parkingslot);
        timestampRepository.save(new Timestamps(parseTimestamp(timestamp),user));
    }

    private Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
