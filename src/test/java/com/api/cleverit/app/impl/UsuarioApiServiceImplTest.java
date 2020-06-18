package com.api.cleverit.app.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.api.cleverit.app.domain.Usuario;
import com.api.cleverit.app.service.UsuarioApiServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioApiServiceImplTest {
	
	private static Usuario usuario = new Usuario();

	@InjectMocks
	private UsuarioApiServiceImpl usuarioApiServiceImpl;

	@Mock
	RestTemplate restTemplate;

	@BeforeAll
	public static void init() {
		usuario.setId("id");
		usuario.setNombre("nombre");
		usuario.setApellido("apellido");
		usuario.setProfesion("profesion");
		usuario.setEmail("email");
		usuario.getId();
		usuario.getNombre();
		usuario.getApellido();
		usuario.getProfesion();
		usuario.getEmail();
		usuario.toString();
	}

	@Test
	public void listTest() {
		String rs = "[\r\n" + "  {\r\n" + "    \"id\": \"vpPU69c\",\r\n" + "    \"nombre\": \"Andres\",\r\n"
				+ "    \"apellido\": \"Varela\",\r\n" + "    \"profesion\": \"Desarrollador\",\r\n"
				+ "    \"email\": \"andres@gmail.com\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": \"hjklmn\",\r\n"
				+ "    \"nombre\": \"Aymar\",\r\n" + "    \"apellido\": \"Noriega\",\r\n"
				+ "    \"profesion\": \"scrum master\",\r\n" + "    \"email\": \"aymar@gmail.com\"\r\n" + "  }]";
		ResponseEntity<String> response = new ResponseEntity<>(rs, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		List<Usuario> list = usuarioApiServiceImpl.list();
		Assert.assertNotNull("Ok", list);
	}

	@Test
	public void createUsuarioTest() {
		String res = "[\r\n" + "  {\r\n" + "    \"id\": \"vpPU69c\",\r\n" + "    \"nombre\": \"Andres\",\r\n"
				+ "    \"apellido\": \"Varela\",\r\n" + "    \"profesion\": \"Desarrollador\",\r\n"
				+ "    \"email\": \"andres@gmail.com\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": \"hjklmn\",\r\n"
				+ "    \"nombre\": \"Aymar\",\r\n" + "    \"apellido\": \"Noriega\",\r\n"
				+ "    \"profesion\": \"scrum master\",\r\n" + "    \"email\": \"aymar@gmail.com\"\r\n" + "  }]";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.POST), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		String create = usuarioApiServiceImpl.createUsuario(usuario);
		Assert.assertNotNull("Ok", create);
	}

	@Test
	public void deleteUsuarioTest() {
		boolean delete = usuarioApiServiceImpl.deleteUsuario(usuario.getId());
		Assert.assertTrue("Ok", delete);
	}

	@Test
	public void editUsuarioTest() {
		String res = "{\r\n" + "    \"id\": \"htjjhkj\",\r\n" + "    \"nombre\": \"Andres\",\r\n"
				+ "    \"apellido\": \"Varela\",\r\n" + "    \"profesion\": \"Desarrollador\",\r\n"
				+ "    \"email\": \"andres@gmail.com\"\r\n" + "  }";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		boolean edit = usuarioApiServiceImpl.editUsuario(usuario);
		Assert.assertTrue("Ok", edit);
	}

	@Test
	public void findUsuarioTest() {
		String res = "{\r\n" + "    \"id\": \"htjjhkj\",\r\n" + "    \"nombre\": \"Andres\",\r\n"
				+ "    \"apellido\": \"Varela\",\r\n" + "    \"profesion\": \"Desarrollador\",\r\n"
				+ "    \"email\": \"andres@gmail.com\"\r\n" + "  }";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		Usuario usuario = usuarioApiServiceImpl.findUsuario("id");
		Assert.assertNotNull("Ok", usuario);
	}

}
