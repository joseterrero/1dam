package com.salesianostriana.dam.tiendamovil.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private ProductoService prodService;
	@Autowired
	private HttpSession session;
	@Autowired
	private UsuarioService usuarioService;

	// Editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Usuario aEditar = usuarioService.findById(id);

		if (aEditar != null) {
			model.addAttribute("formRegistro", aEditar);
			return "admin/addUsuario";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/editarUsuario/submit")
	public String editUsuario(@ModelAttribute("formRegistro") Usuario usuario) {
		usuarioService.edit(usuario);
		return "redirect:/";
	}

	// vista estatica galeria
	@GetMapping("/galeria")
	public String mostrarGaleria() {
		return "galeria";
	}

	// vista estatica informacion
	@GetMapping("/info")
	public String mostrarInfo() {
		return "informacion";
	}

	// vista estatica inicio
	@GetMapping("/")
	public String mostrarInicio() {
		return "inicio";
	}

}
