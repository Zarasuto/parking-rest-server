package tip.project.summer.parkingrestserver.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tip.project.summer.parkingrestserver.jwt.JwtTokenVerifierFilter;
import tip.project.summer.parkingrestserver.jwt.JwtUsernameAndPasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeDetailServiceImpl employeeDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(employeeDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/api/admin/**").hasAnyAuthority("ADMIN_USER")
				.antMatchers("/api/teller/**").hasAnyAuthority("TELLER_USER","ADMIN_USER")
				.antMatchers("/api/security/**").hasAnyAuthority("SECURITY_USER","ADMIN_USER")
				.antMatchers("/login").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
				.addFilterAfter(new JwtTokenVerifierFilter(),JwtUsernameAndPasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
