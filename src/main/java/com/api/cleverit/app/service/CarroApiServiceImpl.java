package com.api.cleverit.app.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.api.cleverit.app.entity.Auto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarroApiServiceImpl implements CarroApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarroApiServiceImpl.class);
	
//	@Value("${api.carro.endpoint}")
	String carroEndpoint = "https://arsene.azurewebsites.net/LicensePlate/";

	@Autowired
	private RestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Override
	public List<Auto> list() {
		List<Auto> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String rs;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(carroEndpoint, HttpMethod.GET, null, String.class);
			rs = response.getBody();
			try {
				list = objectMapper.readValue(rs, new TypeReference<List<Auto>>() {
				});
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage());
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage());
			}
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

}
