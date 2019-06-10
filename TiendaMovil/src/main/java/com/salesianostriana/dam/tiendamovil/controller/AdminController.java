package com.salesianostriana.dam.tiendamovil.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.salesianostriana.dam.tiendamovil.formbean.MensajeBean;
import com.salesianostriana.dam.tiendamovil.formbean.SearchBean;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioService usuarioService;

	@Autowired
	private HttpSession session;

	public AdminController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	// Listar
	@GetMapping({ "/", "/listUsuarios" })
	public String listarUsuarios(Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = (Usuario) usuarioService.findOneByUsername(user.getUsername());
		session.setAttribute("usuarioActual", u);
		model.addAttribute("usuario", u);

		model.addAttribute("inputBuscar", new SearchBean());
		model.addAttribute("lista", usuarioService.findAll());
		return "admin/usuariosAdmin";

	}

	// Editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Usuario aEditar = usuarioService.findById(id);

		if (aEditar != null) {
			model.addAttribute("lista", aEditar);
			return "admin/editUsuarioAdmin";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/editarUsuario/submit")
	public String editUsuario(@ModelAttribute("lista") Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		usuarioService.edit(usuario);
		return "redirect:/admin/listUsuarios";
	}

	// Borrar
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		Usuario usuario = usuarioService.findById(id);
		Usuario sesion = (Usuario) session.getAttribute("usuarioActual");
		ModelAndView mv = new ModelAndView();
		MensajeBean mensaje = null;

		if (usuario.getId() == sesion.getId()) {
			mensaje = new MensajeBean("ERROR ",
					usuario.getUsername() + ", no puede ser eliminado porque es el usuario actual.");
			mv.setViewName("redirect:/admin/listUsuarios");
		} else {
			usuarioService.delete(id);
		}
		return "redirect:/admin/listUsuarios";
	}

	// Buscar
	@PostMapping("/buscarUsuario")
	public String buscarUsuario(@ModelAttribute("inputBuscar") SearchBean s, Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		model.addAttribute("lista", usuarioService.findByNombre(s.getSearch()));
		return "admin/usuariosAdmin";
	}

}
