package com.salesianostriana.dam.tiendamovil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioService usuarioService;

	public AdminController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	// Listar
	@GetMapping({ "/", "/listUsuarios" })
	public String listarTodos(Model model) {
		model.addAttribute("lista", usuarioService.findAll());
		return "admin/usuariosAdmin";

	}

	// Editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Usuario aEditar = usuarioService.findById(id);

		if (aEditar != null) {
			model.addAttribute("lista", aEditar);
			return "admin/añadirUsuario";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/editar/submit")
	public String editUsuario(@ModelAttribute("lista") Usuario usuario) {
		usuarioService.edit(usuario);
		return "redirect:/";
	}

	// Borrar
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		usuarioService.delete(id);
		return "redirect:/";
	}

}
