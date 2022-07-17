package tip.project.summer.parkingrestserver.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tip.project.summer.parkingrestserver.Model.EmployeeRegistrationDTO;
import tip.project.summer.parkingrestserver.Model.Authorities;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            //Map the input into this variable
            EmployeeRegistrationDTO employee = new ObjectMapper().
                    readValue(request.getInputStream(), EmployeeRegistrationDTO.class );
            //Put the already mapped variable into another new variable with Authentication class
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    employee.getUsername(),
                    employee.getPassword(),
                    new ArrayList<Authorities>()
            );

            //Validate the authentication class
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
        String authority= authResult.getAuthorities().iterator().next().getAuthority();
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("data", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(6)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
        response.addHeader("Authorization","Bearer "+token);
        response.addHeader("Expect",authority);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Authentication Failed");
    }
}
