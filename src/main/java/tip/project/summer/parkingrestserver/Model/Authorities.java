package tip.project.summer.parkingrestserver.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","authority"}))
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="authority")
    private String authority;

    @Column(name="employee_id",nullable = false)
    private Long employee_id;

    public Authorities(String authority, Long employee_id) {
        this.authority = authority;
        this.employee_id = employee_id;
    }

    public Authorities() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee) {
        this.employee_id = employee;
    }

    public void setAuthority(String authority){
        this.authority=authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
