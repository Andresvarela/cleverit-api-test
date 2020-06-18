package com.api.cleverit.app.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.api.cleverit.app.entity.Auto;
import com.api.cleverit.app.service.CarroApiServiceImpl;

@SpringBootTest
public class CarroApiServiceImplTest {
	
	@InjectMocks
	private CarroApiServiceImpl carroServiceImpl;

	@Mock
	RestTemplate restTemplate;
	
	public static final Auto auto = new Auto();
	
	@Test
	public void autoTest() {
		auto.setId("id");
		auto.setTipoAuto("tipoAuto");
		auto.setPatente("patente");
		auto.setColor("color");
		auto.getId();
		auto.getTipoAuto();
		auto.getPatente();
		auto.getColor();
		auto.toString();
	}
	
	@Test
	public void ListTest() {
		String rs = "[\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"adfdkl\",\r\n" + 
				"    \"patente\": \"GYSS81\",\r\n" + 
				"    \"tipoAuto\": \"Sedan\",\r\n" + 
				"    \"color\": \"Rojo\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"mbXqx35\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"dIZXamf\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"c_BauuS\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"L_yrccy\"\r\n" + 
				"  }\r\n" + 
				"]";
		ResponseEntity<String> response = new ResponseEntity<>(rs, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		List<Auto> list = carroServiceImpl.list();
	}
}
