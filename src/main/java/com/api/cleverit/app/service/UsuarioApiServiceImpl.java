package com.api.cleverit.app.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.api.cleverit.app.domain.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UsuarioApiServiceImpl implements UsuarioApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioApiServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();
//
//	@Value("${api.user.endpoint}")
	String userEndpoint = "http://arsene.azurewebsites.net/User";

	@Override
	public List<Usuario> list() {
		List<Usuario> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String res;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(userEndpoint, HttpMethod.GET, null, String.class);
			res = response.getBody();
			try {
				list = objectMapper.readValue(res, new TypeReference<List<Usuario>>() {
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

	@Override
	public Usuario findUsuario(String id) {

		Usuario usuario = new Usuario();
		String response;

		ObjectMapper objectMapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> rs = null;
		try {
			rs = restTemplate.exchange(userEndpoint + id, HttpMethod.GET, null, String.class);
			response = rs.getBody();
			try {
				usuario = objectMapper.readValue(response, new TypeReference<Usuario>() {
				});
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage());
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage());
			}
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return usuario;
	}

	@Override
	public String createUsuario(Usuario usuario) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Usuario> entity = new HttpEntity<>(usuario, headers);
		ResponseEntity<String> response = null;
		String rs = null;
		try {
			response = restTemplate.exchange(userEndpoint, HttpMethod.POST, entity, String.class);
			rs = response.getBody();
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return rs;
	}

	@Override
	public boolean deleteUsuario(String id) {
		boolean rs = false;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(userEndpoint + id, HttpMethod.DELETE, null, String.class);
			rs = true;
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return rs;
	}

	@Override
	public boolean editUsuario(Usuario usuario) {
		boolean rs= false;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Usuario> entity = new HttpEntity<>(usuario, headers);
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(userEndpoint + usuario.getId(), HttpMethod.PUT, entity,
					String.class);
			rs = true;
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}		
		return rs;
	}
}
