package tip.project.summer.parkingrestserver.Model;

import java.util.ArrayList;

public class UserDTOWithTimestampList {
    private String uid;
    private String parking_slot;
    private ArrayList<String> timeStamp;
    private ArrayList<String> signouts;
    private String contactNum;
    private String plateNum;

    public ArrayList<String> getSignouts() {
        return signouts;
    }

    public void setSignouts(ArrayList<String> signouts) {
        this.signouts = signouts;
    }

    public UserDTOWithTimestampList() {
        this.timeStamp = new ArrayList<>();
        this.signouts = new ArrayList<>();
    }

    public String getParking_slot() {
        return parking_slot;
    }

    public void setParking_slot(String parking_slot) {
        this.parking_slot = parking_slot;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<String> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ArrayList<String> timeStamp) {
        this.timeStamp = timeStamp;
    }
}
