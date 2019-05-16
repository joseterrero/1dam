package com.salesianostriana.dam.tiendamovil.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.formbean.LoginUser;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService userService;

	@Autowired
	private HttpSession session;

	@GetMapping({ "/login" })
	public String showLogin(Model model) {
		if (session.getAttribute("usuarioActual") == null) {
			model.addAttribute("loginUser", new LoginUser());
			model.addAttribute("cuenta", new Usuario());
			return "login";
		} else {
			return "redirect:/admin/añadirProductoAdmin";
		}

	}

	@PostMapping("/checkLogin")
	public String doLogin(@ModelAttribute("loginUser") LoginUser loginUser, BindingResult bindingResult, Model model) {
		Usuario cuenta = userService.validateUser(loginUser.getNomUsuario(), loginUser.getContrasenya());
		if (cuenta != null) {
			session.setAttribute("usuarioActual", cuenta);
			return "redirect:/admin/añadirProductoAdmin";
		} else {
			model.addAttribute("cuenta", new Usuario());
			model.addAttribute("loginError", "El usuario o contraseña no es válido");
			return "login";
		}

	}

	@GetMapping("/logout")
	public String doLogout(Model model) {
		session.removeAttribute("usuarioActual");
		return "redirect:/";
	}

}
