package ec.unexus.prueba.servicio.tony.veas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.controllers.DireccionController;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.services.DireccionService;

public class DireccionControllerTest {

	// Declaramos el mock de ClienteRepository
	@Mock
	private DireccionService direccionService;

	/*
	 * Usamos @InjectMocks para que Mockito cree una instancia de
	 * DireccionController
	 * e inyecte el mock de ClienteRepository en él.
	 */
	@InjectMocks
	private DireccionController direccionController;

	// Inicialización de variables que se usan en las pruebas
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAgregarDireccionClienteExistente() {
		// Instanciando objetos de pruebas
		Integer idCliente = 1;
		DireccionDTO direccionDTO = new DireccionDTO();
		Direccion direccion = new Direccion();
		when(direccionService.agregarDireccion(idCliente, direccionDTO)).thenReturn(direccion);
		ResponseEntity<Direccion> response = direccionController.agregarDireccion(idCliente, direccionDTO);
		// Aserciones y verificaciones
		verify(direccionService, times(1)).agregarDireccion(idCliente, direccionDTO);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(direccion, response.getBody());
	}

	@Test
	public void testAgregarDireccionClienteNoExistente() {
		// Instanciando objetos de pruebas
		Integer idCliente = 1;
		DireccionDTO direccionDTO = new DireccionDTO();
		when(direccionService.agregarDireccion(idCliente, direccionDTO))
				.thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
		try {
			direccionController.agregarDireccion(idCliente, direccionDTO);
		} catch (ResponseStatusException e) {
			// Aserciones y verificaciones
			verify(direccionService, times(1)).agregarDireccion(idCliente, direccionDTO);
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			assertEquals("Cliente no encontrado", e.getReason());
		}
	}

	@Test
	public void testGetDireccionesClienteExistente() {
		// Instanciando objetos de pruebas
		Integer idCliente = 1;
		ClienteDireccionesDTO clienteDireccionesDTO = new ClienteDireccionesDTO();
		// Configurando comportamiento de los mocks
		when(direccionService.getDireccionesCliente(idCliente)).thenReturn(clienteDireccionesDTO);
		ResponseEntity<ClienteDireccionesDTO> response = direccionController.getDireccionesCliente(idCliente);
		// Aserciones y verificaciones
		verify(direccionService, times(1)).getDireccionesCliente(idCliente);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(clienteDireccionesDTO, response.getBody());
	}

	@Test
	public void testGetDireccionesClienteNoExistente() {
		// Instanciando objetos de pruebas
		Integer idCliente = 1;
		// Configurando comportamiento de los mocks
		when(direccionService.getDireccionesCliente(idCliente))
				.thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
		try {
			direccionController.getDireccionesCliente(idCliente);
		} catch (ResponseStatusException e) {
			// Aserciones y verificaciones
			verify(direccionService, times(1)).getDireccionesCliente(idCliente);
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			assertEquals("Cliente no encontrado", e.getReason());
		}
	}

}
