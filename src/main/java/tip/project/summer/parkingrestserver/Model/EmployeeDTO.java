package tip.project.summer.parkingrestserver.Model;

public class EmployeeDTO {

    private Long id;
    private String role;
    private String username;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String role, String username) {
        this.id = id;
        this.role = role;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
