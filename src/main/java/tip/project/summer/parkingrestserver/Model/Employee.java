package tip.project.summer.parkingrestserver.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "employee",fetch=FetchType.EAGER)
    private List<Roles> rolesList;


    public Employee() {
        this.enabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<Roles> getAuthorities() {
        return rolesList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
