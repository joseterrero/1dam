package com.salesianostriana.dam.tiendamovil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.tiendamovil.modelo.Producto;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.repository.ProductoRepository;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productRepo;

	@Autowired
	private ProductoService productService;

	@GetMapping("/admin/addProducto")
	public String mostrarFormRegistroAdmin(Model model) {
		model.addAttribute("formProductoAdmin", new Usuario());
		return "admin/añadirProductoAdmin";
	}

	@PostMapping("/newProductoAdmin")
	public String nuevoUsuarioAdmin(@ModelAttribute("formProductoAdmin") Producto usuario, BindingResult bindingResult,
			Model model) {
		productRepo.save(usuario);
		return "redirect:/admin/productosAdmin";
	}

}
