package tip.project.summer.parkingrestserver.Model;

import javax.persistence.*;

@Entity
@Table(name="parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="slot_name")
    private String slot_name;

    @Column(name="enabled")
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlot_name() {
        return slot_name;
    }

    public void setSlot_name(String slot_name) {
        this.slot_name = slot_name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

