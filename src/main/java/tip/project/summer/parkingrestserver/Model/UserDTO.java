package tip.project.summer.parkingrestserver.Model;

import java.sql.Timestamp;

public class UserDTO {
    private String UID;
    private String parking_slot;
    private String timeStamp;
    private double contactNum;
    private String plateNum;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getParking_slot() {
        return parking_slot;
    }

    public void setParking_slot(String parking_slot) {
        this.parking_slot = parking_slot;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getContactNum() {
        return contactNum;
    }

    public void setContactNum(double contactNum) {
        this.contactNum = contactNum;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
}
