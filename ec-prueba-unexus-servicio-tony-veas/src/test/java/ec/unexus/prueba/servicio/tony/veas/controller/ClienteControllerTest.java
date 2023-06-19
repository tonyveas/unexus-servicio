package ec.unexus.prueba.servicio.tony.veas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.controllers.ClienteController;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.services.ClienteService;

public class ClienteControllerTest {

	// Declaramos el mock de ClienteRepository
	@Mock
	private ClienteService clienteService;

	/* 
	 * Usamos @InjectMocks para que Mockito cree una instancia de ClienteController
	 * e inyecte el mock de ClienteService en él. 
	 */
	@InjectMocks
	private ClienteController clienteController;

	// Inicialización de mocks
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarClientesSinSearch() {
		// Instanciando objetos de pruebas
		ClienteDTO cliente1 = new ClienteDTO();
		ClienteDTO cliente2 = new ClienteDTO();
		List<ClienteDTO> listaClientes = Arrays.asList(cliente1, cliente2);
		// Configurando comportamiento de los mocks
		when(clienteService.buscarClientes(null)).thenReturn(listaClientes);
		ResponseEntity<List<ClienteDTO>> response = clienteController.buscarClientes(null);
		// Aserciones y verificaciones
		verify(clienteService, times(1)).buscarClientes(null);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, response.getBody().size());
	}

	@Test
	public void testBuscarClientesConSearch() {
		// Instanciando objetos de pruebas
		ClienteDTO cliente1 = new ClienteDTO();
		List<ClienteDTO> listaClientes = Arrays.asList(cliente1);
		String search = "1234";
		// Configurando return de los mocks
		when(clienteService.buscarClientes(search)).thenReturn(listaClientes);
		ResponseEntity<List<ClienteDTO>> response = clienteController.buscarClientes(search);
		// Aserciones y verificaciones
		verify(clienteService, times(1)).buscarClientes(search);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(1, response.getBody().size());
	}

	@Test
	public void testCrearClienteExitoso() {
		// Instanciando objetos de pruebas
		ClienteDTO nuevoClienteDTO = new ClienteDTO();
		Cliente nuevoCliente = new Cliente();
		// Configurando comportamiento de los mocks
		when(clienteService.createCliente(nuevoClienteDTO)).thenReturn(nuevoCliente);
		ResponseEntity<Cliente> response = clienteController.crearCliente(nuevoClienteDTO);
		// Aserciones y verificaciones
		verify(clienteService, times(1)).createCliente(nuevoClienteDTO);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(nuevoCliente, response.getBody());
	}

	@Test
	public void testCrearClienteExistente() {
		// Instanciando objetos de pruebas
		ClienteDTO nuevoClienteDTO = new ClienteDTO();
		// Configurando comportamiento de los mocks
		when(clienteService.createCliente(nuevoClienteDTO)).thenThrow(
				new IllegalArgumentException("Ya existe un cliente con el número de identificación proporcionado"));
		try {
			clienteController.crearCliente(nuevoClienteDTO);
		} catch (ResponseStatusException e) {
			// Aserciones
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			assertEquals("Ya existe un cliente con el número de identificación proporcionado", e.getReason());
		}
		// verificaciones
		verify(clienteService, times(1)).createCliente(nuevoClienteDTO);
	}

	@Test
	public void testEditarClienteExitoso() {
		// Instanciando objetos de pruebas
		Integer id = 1;
		ClienteDTO clienteDTO = new ClienteDTO();
		ClienteDTO clienteActualizadoDTO = new ClienteDTO();
		// Configurando comportamiento de los mocks
		when(clienteService.update(id, clienteDTO)).thenReturn(clienteActualizadoDTO);
		ResponseEntity<ClienteDTO> response = clienteController.editarCliente(id, clienteDTO);
		// Aserciones y verificaciones
		verify(clienteService, times(1)).update(id, clienteDTO);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(clienteActualizadoDTO, response.getBody());
	}

	@Test
	public void testEditarClienteExistente() {
		// Instanciando objetos de pruebas
		Integer id = 1;
		ClienteDTO clienteDTO = new ClienteDTO();
		// Configurando comportamiento de los mocks
		when(clienteService.update(id, clienteDTO)).thenThrow(
				new IllegalArgumentException("Ya existe un cliente con el número de identificación proporcionado"));
		try {
			clienteController.editarCliente(id, clienteDTO);
		} catch (ResponseStatusException e) {
			// Aserciones
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			assertEquals("Ya existe un cliente con el número de identificación proporcionado", e.getReason());
		}
		// verificaciones
		verify(clienteService, times(1)).update(id, clienteDTO);
	}

	@Test
	public void testEliminarClienteExistente() {
		// Instanciando objetos de pruebas
		Integer id = 1;
		// Configurando comportamiento de los mocks
		doNothing().when(clienteService).eliminarCliente(id);
		ResponseEntity<Void> response = clienteController.eliminarCliente(id);
		// Aserciones y verificaciones
		verify(clienteService, times(1)).eliminarCliente(id);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testEliminarClienteNoExistente() {
		// Instanciando objetos de pruebas
		Integer id = 1;
		// Configurando comportamiento de los mocks
		doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un cliente con el ID proporcionado"))
				.when(clienteService).eliminarCliente(id);
		try {
			clienteController.eliminarCliente(id);
		} catch (ResponseStatusException e) {
			// Aserciones y verificaciones
			verify(clienteService, times(1)).eliminarCliente(id);
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			assertEquals("No se encontró un cliente con el ID proporcionado", e.getReason());
		}
	}

}
