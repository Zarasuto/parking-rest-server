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

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    public Roles(String role ,Employee employee) {
        this.role=role;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
