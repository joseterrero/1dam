package com.salesianostriana.dam.tiendamovil.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.tiendamovil.exception.BorrarUsuarioException;
import com.salesianostriana.dam.tiendamovil.formbean.SearchBean;
import com.salesianostriana.dam.tiendamovil.modelo.Pager;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.PedidoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private HttpSession session;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	public AdminController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	// Listar
	@GetMapping({ "/", "/listUsuarios" })
	public String listarUsuarios(Model model) {

		model.addAttribute("usuario", session.getAttribute("usuarioActual"));

		model.addAttribute("inputBuscar", new SearchBean());
		model.addAttribute("lista", usuarioService.findAll());
		return "admin/usuariosAdmin";

	}

	// Editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		model.addAttribute("usuario", session.getAttribute("usuarioActual"));

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
	public String borrar(@PathVariable("id") long id, Model model) throws BorrarUsuarioException {
		Usuario usuario = usuarioService.findById(id);
		Usuario sesion = (Usuario) session.getAttribute("usuarioActual");
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));

		if (usuario.getId() == sesion.getId()) {
			throw new BorrarUsuarioException("ERROR. No puede ser eliminado porque es el usuario actual.");
		} else {
			for (Pedido pedido : pedidoService.findAll()) {
				if (pedido.getUsuario().getId() == id) {
					pedido.setUsuario(null);
				}
			}
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

	// Vista Registro
	@GetMapping("/listPedidos")
	public String mostrarPedidos(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("id") Optional<Long> id, Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = (Usuario) usuarioService.findOneByUsername(user.getUsername());
		session.setAttribute("usuarioActual", u);

		model.addAttribute("usuario", session.getAttribute("usuarioActual"));

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Pedido> pedidos;
		pedidos = pedidoService.findByUsuario(u, PageRequest.of(evalPage, evalPageSize));

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(pedidos.getTotalPages(), pedidos.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("pedidosAdmin", pedidos);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		model.addAttribute("usuario", u);
		return "admin/pedidosAdmin";
	}

	// Precio total pedidos
	@ModelAttribute("totalGastadoAdmin")
	public Double calcularTotalCarrito() {
		List<Pedido> lista = pedidoService.findAll();
		double total = 0;

		if (lista != null) {
			for (Pedido pedido : lista) {
				total += pedido.getPrecioFinal();
			}
			return total;
		}
		return 0.0;
	}

}
