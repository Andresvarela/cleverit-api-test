package com.api.cleverit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.api.cleverit.app.domain.Usuario;
import com.api.cleverit.app.service.UsuarioApiService;

@Controller
public class UsuarioController {

	@Autowired
	private final UsuarioApiService usuarioService;

	@Autowired
	public UsuarioController(UsuarioApiService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("titulo", "Api Cleverit");
		model.addAttribute("usuarios", usuarioService.list());
		return "index";
	}

	@GetMapping("/crear")
	public String nuevoUsuario(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("titulo", "Nuevo Usuario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/crear")
	public String crearUsuario(Usuario usuario, RedirectAttributes model) {
		usuarioService.createUsuario(usuario);
		return "redirect:index";
	}

	@GetMapping("/ver/{id}")
	public String editarUsuario(@PathVariable(name ="id") String id, Model model) {
		Usuario usuario = usuarioService.findUsuario(id);
		model.addAttribute("usuario", usuario);
		return "ver";
	}

	@PostMapping("/actualizar")
	public String editarUsuario(Usuario usuario) {
		return (usuarioService.editUsuario(usuario)) ? "redirect:/index" : "actualizar";
	}

	@GetMapping("/eliminar")
	public String eliminarUsuario(@RequestParam(name = "id", required = false) String id) {
		usuarioService.deleteUsuario(id);
		return "redirect:/";
	}
}
