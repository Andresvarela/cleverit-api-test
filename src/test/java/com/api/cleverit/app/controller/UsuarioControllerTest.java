package com.api.cleverit.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeTypeUtils;

import com.api.cleverit.app.domain.Usuario;
import com.api.cleverit.app.service.UsuarioApiService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioApiService usuarioApiService;

	@Test
	public void testIndex() throws Exception {
		final ResultActions result = mockMvc.perform(get("/").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void nuevoUsuarioTest() throws Exception {
		final ResultActions result = mockMvc.perform(get("/crear").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void crearUsuarioTest() throws Exception {
		String rs=null;
		Mockito.when(usuarioApiService.createUsuario(Mockito.any(Usuario.class))).thenReturn(rs);
		final ResultActions result = mockMvc.perform(post("/crear").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void editarUsuarioTest() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "1");
		Mockito.when(usuarioApiService.findUsuario(Mockito.any(String.class))).thenReturn(new Usuario());
		final ResultActions result = mockMvc
				.perform(get("/actualizar").accept(MimeTypeUtils.APPLICATION_JSON_VALUE).params(requestParams));
		Assert.assertNotNull("Ok", result);
	}
	
	@Test
	public void editarUsuarioPostTest() throws Exception {
		Mockito.when(usuarioApiService.editUsuario(Mockito.any(Usuario.class))).thenReturn(true);
		final ResultActions result = mockMvc.perform(post("/actualizar").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void eliminarUsuarioTest() throws Exception {
		final ResultActions result = mockMvc.perform(get("/eliminar").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

}
