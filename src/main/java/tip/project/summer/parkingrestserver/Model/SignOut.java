package tip.project.summer.parkingrestserver.Model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "signout")
public class SignOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="timestmap")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public SignOut(Timestamp timestamp, User user) {
        this.timestamp = timestamp;
        this.user = user;
    }

    public SignOut() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
