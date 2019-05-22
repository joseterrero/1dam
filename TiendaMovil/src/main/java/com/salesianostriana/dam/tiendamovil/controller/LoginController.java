package com.salesianostriana.dam.tiendamovil.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.formbean.LoginUser;
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

//	@GetMapping("/login")
//	public String mostrarLogin(Model model) {
//		model.addAttribute("loginUser", new Usuario());
//		return "login";
//
//	}
//
//	@PostMapping("/checkLogin")
//	public String doLogin(@ModelAttribute("loginUser") Usuario usuario, Model model) {
//		Usuario user = usuarioRepo.findFirstByNomUsuarioAndContrasenya(loginUser.getNomUsuario(),
//				loginUser.getContrasenya());
//
//		if (user != null && user.isAdmin()) {
//
//			session.setAttribute("usuarioActual", user);
//			return "redirect:/admin/listProductos";
//		} else if (user != null) {
//			session.setAttribute("usuarioActual", user);
//			return "redirect:/inicio";
//
//		} else {
//			model.addAttribute("loginError", "El usuario o contraseña no es válido");
//			return "login";
//		}
//
//	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		session.removeAttribute("usuarioActual");
		return "redirect:/login";
	}

}
