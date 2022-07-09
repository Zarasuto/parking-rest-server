package tip.project.summer.parkingrestserver.Model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="UID", nullable = false)
    private String uid;

    @OneToMany(mappedBy = "user", fetch= FetchType.EAGER)
    private List<Timestamps> timestamps;

    @Column(name="parking_slot")
    private String parking_slot;

    @Column(name="contactNum")
    private Double contactnum;

    @Column(name="plateNum")
    private String platenum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Timestamps> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<Timestamps> timestamps) {
        this.timestamps = timestamps;
    }

    public Double getContactnum() {
        return contactnum;
    }

    public void setContactnum(Double contactnum) {
        this.contactnum = contactnum;
    }

    public String getPlatenum() {
        return platenum;
    }

    public void setPlatenum(String platenum) {
        this.platenum = platenum;
    }

    public String getParking_slot() {
        return parking_slot;
    }

    public void setParking_slot(String parking_slot) {
        this.parking_slot = parking_slot;
    }
}
