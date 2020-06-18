package com.api.cleverit.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MimeTypeUtils;

import com.api.cleverit.app.entity.Auto;
import com.api.cleverit.app.repository.ICarro;
import com.api.cleverit.app.service.CarroApiService;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CarroController.class)
public class CarroControllerTest {
	
	private static List<Auto> autos;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarroApiService carroApiService;

	@MockBean
	private ICarro carroRepository;


	@Test
	public void nuevaPatenteTest() throws Exception {
		autos = new ArrayList<Auto>();
		Auto auto = new Auto();
		auto.setId("id");
		auto.setTipoAuto("tipoAuto");
		auto.setPatente("patente");
		auto.setColor("color");
		autos.add(auto);
		
		Mockito.when(carroApiService.list()).thenReturn(autos);
		Mockito.when(carroRepository.saveAll(Mockito.anyList())).thenReturn(autos);
		final ResultActions result = mockMvc.perform(get("/licencias").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

}
