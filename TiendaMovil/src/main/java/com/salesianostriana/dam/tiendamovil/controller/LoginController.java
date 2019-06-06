package com.salesianostriana.dam.tiendamovil.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.UsuarioRepository;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String mostrarLogin(Model model) {
		
		return "login";

	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		return "redirect:/login";
	}

}
