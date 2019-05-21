package com.salesianostriana.dam.tiendamovil.security;

//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Service("userDetailsService")
public class UserDetailsServiceImpl // implements UserDetailsService 
{

//	private UsuarioService usuarioService;
//
//	public UserDetailsServiceImpl(UsuarioService servicio) {
//		this.usuarioService = servicio;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String nomUsuario) throws UsernameNotFoundException {
//
//		Usuario usuario = usuarioService.findOneByNomUsuario(nomUsuario);
//
//		UserBuilder userBuilder = null;
//
//		if (usuario != null) {
//			userBuilder = User.withUsername(usuario.getNomUsuario());
//			userBuilder.disabled(false);
//			userBuilder.password(usuario.getContrasenya());
//			if (usuario.isAdmin()) {
//				// Este caso indica que un ADMIN también puede hacer todo lo que hace un USER
//				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"),
//						new SimpleGrantedAuthority("ROLE_ADMIN"));
//			} else {
//				userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
//			}
//		} else {
//			throw new UsernameNotFoundException("Usuario no encontrado");
//		}
//
//		return userBuilder.build();
//
//	}

}