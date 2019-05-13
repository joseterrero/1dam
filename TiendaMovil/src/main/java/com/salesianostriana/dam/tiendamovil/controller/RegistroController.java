package com.salesianostriana.dam.tiendamovil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.UsuarioRepository;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class RegistroController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/addUsuario")
	public String mostrarFormLogin(Model model) {
		model.addAttribute("registroForm", new Usuario());
		return "registro";
	}
	
	@PostMapping("/newUsuario")
	public String nuevoUsuario(@ModelAttribute("registroForm") Usuario usuario, BindingResult bindingResult,
			Model model) {
		if (usuarioService.findOneByUsername(usuario.getNomUsuario()) != null || usuarioService.findOneByEmail(usuario.getCorreo()) != null) {
			model.addAttribute("errorRegistro", "El usuario o el correo electrónico ya existe");
			return "registro";
		} else {
			usuarioRepository.save(usuario);
			return "redirect:/index";
		}

	}

}
