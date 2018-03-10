package es.gestorincidencias.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.gestorincidencias.entidades.Usuario;
import es.gestorincidencias.repositorios.UsuarioRepository;

@Component
public class UserRepositoryAuthenticationProvider  implements AuthenticationProvider{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Usuario user = usuarioRepo.findByEmail(auth.getName());

		if (user == null) {
			throw new BadCredentialsException("User not found");
		}

		String password = (String) auth.getCredentials();
		if (!new BCryptPasswordEncoder().matches(password, user.getPass())) {
			throw new BadCredentialsException("Wrong password");
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(user.getRol().getRol()));
		

		return new UsernamePasswordAuthenticationToken(user.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
