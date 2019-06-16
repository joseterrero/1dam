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
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.tiendamovil.modelo.LineaPedido;
import com.salesianostriana.dam.tiendamovil.modelo.Pager;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.modelo.Usuario;
import com.salesianostriana.dam.tiendamovil.service.LineaPedidoService;
import com.salesianostriana.dam.tiendamovil.service.PedidoService;
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
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private LineaPedidoService linPedService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	// Editar
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdit(@PathVariable("id") long id, Model model) {

		Usuario aEditar = usuarioService.findById(id);

		if (aEditar != null) {
			model.addAttribute("formRegistro", aEditar);
			return "editUsuario";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/editarUsuario/submit")
	public String editUsuario(@ModelAttribute("formRegistro") Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		usuarioService.edit(usuario);
		return "redirect:/";
	}

	// vista estatica galeria
	@GetMapping("/galeria")
	public String mostrarGaleria(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		return "galeria";
	}

	// vista estatica informacion
	@GetMapping("/info")
	public String mostrarInfo(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		return "informacion";
	}

	// vista estatica inicio
	@GetMapping({ "/", "/historicos" })
	public String mostrarInicio(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("id") Optional<Long> id, Model model) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario u = (Usuario) usuarioService.findOneByUsername(user.getUsername());
		session.setAttribute("usuarioActual", u);

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Pedido> pedidos = null;
		pedidos = pedidoService.findByUsuario(u, PageRequest.of(evalPage, evalPageSize));

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(pedidos.getTotalPages(), pedidos.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("pedidos", pedidos);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		model.addAttribute("usuario", u);
		return "inicio";
	}

	// Precio total pedidos historicos
	@ModelAttribute("totalGastado")
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

	@GetMapping("/historico/detalles/{id}")
	public String mostrarHistoricoLineaPedidos(@PathVariable("id") long id,
			@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page,
			Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));

		Pedido pedido = pedidoService.findById(id);
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<LineaPedido> lineasPedido = null;
		lineasPedido = linPedService.findByPedido(pedido, PageRequest.of(evalPage, evalPageSize));

		Pager pager = new Pager(lineasPedido.getTotalPages(), lineasPedido.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("lineasPedido", lineasPedido);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		return "historicoDetalles";
	}

}
