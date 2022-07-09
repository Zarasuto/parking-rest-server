package tip.project.summer.parkingrestserver.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="roles",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","role"}))
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="role")
    private String role;

    @Column(name="employee_id")
    private Long employee_id;

    public Roles(String role , Long employee_id) {
        this.role=role;
        this.employee_id = employee_id;
    }

    public Roles() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee) {
        this.employee_id = employee;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
