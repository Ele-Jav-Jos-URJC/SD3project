package es.gestorincidencias.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UserRepositoryAuthenticationProvider  authenticationProvider;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/prueba").permitAll();
		http.authorizeRequests().antMatchers("/incidencias").permitAll();
		http.authorizeRequests().antMatchers("/listaincidencias").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/search").permitAll();
		
		http.authorizeRequests().antMatchers("/datosexito").hasAnyRole("USER","ADMIN","TECH");
		http.authorizeRequests().antMatchers("/nuevaincidencia").hasAnyRole("USER","ADMIN","TECH");
		http.authorizeRequests().antMatchers("/nuevousuario").hasAnyRole("USER","ADMIN","TECH");
		http.authorizeRequests().antMatchers("/guardarincidencia").hasAnyRole("USER","ADMIN","TECH");
		http.authorizeRequests().antMatchers("/inipage").hasAnyRole("USER","ADMIN","TECH");
		//http.authorizeRequests().anyRequest().authenticated();
		
		http.authorizeRequests().antMatchers("/incidencias").permitAll();
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("usuario");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/inipage");
		http.formLogin().failureUrl("/loginerror");
		
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/login");
		
		
		http.csrf().disable();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("j").password("").roles("ADMIN");
		auth.authenticationProvider(authenticationProvider);
	}
}
