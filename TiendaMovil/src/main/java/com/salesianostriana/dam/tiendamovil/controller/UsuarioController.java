package com.salesianostriana.dam.tiendamovil.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.tiendamovil.modelo.Pager;
import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class UsuarioController {

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

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
	public String mostrarGaleria(Model model) {
		return "galeria";
	}

	// vista estatica informacion
	@GetMapping("/info")
	public String mostrarInfo(Model model) {
		return "informacion";
	}

	// vista producto
	@GetMapping("/productos")
	public String verLiquidos(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

//		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
//		// el tamaño de página inicial.
//		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
//
//		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
//		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
//		// del parámetro decrementado en 1.
//		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
//
//		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
//		// nuestro modelo
//		Page<Producto> productos = prodService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
//		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
//		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
//		// botones
//		// debe mostrar y cuál es el número de objetos a dibujar.
//		Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);
//
//		model.addAttribute("productos", productos);
//		model.addAttribute("selectedPageSize", evalPageSize);
//		model.addAttribute("pageSizes", PAGE_SIZES);
//		model.addAttribute("pager", pager);

		return "productos";

	}

}
