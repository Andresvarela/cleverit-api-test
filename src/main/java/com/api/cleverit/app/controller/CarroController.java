package com.api.cleverit.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.api.cleverit.app.entity.Auto;
import com.api.cleverit.app.repository.ICarro;
import com.api.cleverit.app.service.CarroApiService;

@Controller
public class CarroController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarroController.class);

	@Autowired
	private final CarroApiService carroApiService;

	@Autowired
	private ICarro carroRepository;

	@Autowired
	public CarroController(CarroApiService carroApiService) {
		this.carroApiService = carroApiService;
	}

	@GetMapping("/licencias")
	public String nuevaPatente(Model model) {
		try {
			List<Auto> list = carroApiService.list();

			// La base de datos utilizada es MYSQL
			// La configuracion de conexion se encuentra en application.properties
			carroRepository.saveAll(list);
			
			model.addAttribute("mensaje", "Las Patentes se han Guardado con Exito !!");
			model.addAttribute("licencias", list);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			model.addAttribute("mensaje", "Opps ! Ha ocurrido un error.. intente de nuevo mas tarde");
		}
		return "carro";
	}

}
