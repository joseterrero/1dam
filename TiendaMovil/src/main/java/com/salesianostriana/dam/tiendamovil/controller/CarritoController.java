package com.salesianostriana.dam.tiendamovil.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.tiendamovil.modelo.LineaPedido;
import com.salesianostriana.dam.tiendamovil.modelo.Pedido;
import com.salesianostriana.dam.tiendamovil.service.CarritoService;
import com.salesianostriana.dam.tiendamovil.service.LineaPedidoService;
import com.salesianostriana.dam.tiendamovil.service.PedidoService;
import com.salesianostriana.dam.tiendamovil.service.ProductoService;
import com.salesianostriana.dam.tiendamovil.service.UsuarioService;

@Controller
public class CarritoController {

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20, 50 };

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CarritoService carritoService;
	@Autowired
	private LineaPedidoService linPedService;
	@Autowired
	private ProductoService productService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private HttpSession session;

	@Autowired
	public CarritoController(CarritoService carritoService, LineaPedidoService linPedService) {
		this.carritoService = carritoService;
		this.linPedService = linPedService;
	}

	// Vista carrito
	@GetMapping("/carrito")
	public String mostrarCarrito(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		if (model.addAttribute("products", carritoService.getProductsInCart()) == null) {
			return "redirect:/";
		}
		return "carrito";
	}

	// Añadir producto al carrito
	@GetMapping("/carrito/add/{id}")
	public String addProductoCarrito(@PathVariable("id") long id, Model model) {
		carritoService.addLineaPedido(productService.findById(id));
		return "redirect:/list";
	}

	// Eliminar producto del carrito
	@GetMapping("/carrito/remove/{id}")
	public String borrarProductoCarrito(@PathVariable("id") long id) {
		int index = 0;

		List<LineaPedido> lista = carritoService.getProductsInCart();

		for (LineaPedido linPed : lista) {
			if (linPed.getProducto() == productService.findById(id)) {
				index = lista.indexOf(linPed);
			}
		}
		carritoService.borrarLineaPedido(index);
		return "redirect:/carrito";
	}

	// Precio total carrito
	@ModelAttribute("totalCarrito")
	public Double calcularTotalCarrito() {
		List<LineaPedido> lista = carritoService.getProductsInCart();
		double total = 0;

		if (lista != null) {
			for (LineaPedido linPed : lista) {
				total += linPed.getProducto().getPrecio() * linPed.getCantidad();
			}
			return total;
		}
		return 0.0;
	}
	
	// Comprobacion carrito
	@GetMapping("/carrito/checkout")
	public String comprobarCompra(Model model) {
		model.addAttribute("usuario", session.getAttribute("usuarioActual"));
		
		List<LineaPedido> listaPedidos = carritoService.getProductsInCart();
		Pedido pedido = new Pedido();
		
		for (LineaPedido linPed : listaPedidos) {
			linPed.setPedido(pedido);
		}
		pedido.setFecha(LocalDate.now());
		pedido.setPrecioFinal(carritoService.calculoPrecioFinal());
		pedido.setLista(listaPedidos);
//		pedido.setUsuario(usuario);
		
		model.addAttribute("pedido", pedidoService.save(pedido));
		carritoService.limpiarCarrito();
		
		return "productos";
	}

}
