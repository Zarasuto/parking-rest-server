package tip.project.summer.parkingrestserver.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tip.project.summer.parkingrestserver.Services.EmployeeServiceImpl;

@Component
public class EmployeeDetailServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadByUsername(username);
    }

}
