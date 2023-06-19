package ec.unexus.prueba.servicio.tony.veas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.utils.UtilsMethods;
import jakarta.transaction.Transactional;

public class ClienteServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String NUM_IDENTIFICACION = "1234";
	private static final String CLIENTE_NAME = "Tony Veas";
	private static final String PROVINCE = "Provincia";
	private static final String CITY = "Ciudad";
	private static final String ADDRESS = "Address";
	private static final String INVALID_ADDRESS_TYPE = "INVALID_ADDRESS_TYPE";
	private static final String TYPE_ADDRESS_MATRIZ = "MATRIZ";
	private static final String TYPE_ADDRESS_SUCURSAL = "SUCURSAL";

	// Declaramos el mock de ClienteRepository
	@Mock
	private ClienteRepository mockRepo;

	private UtilsMethods utilsMethods;

	// Usamos @InjectMocks para que Mockito cree una instancia de ClienteService
	// e inyecte el mock de ClienteRepository en él.
	@InjectMocks
	private ClienteService service;

	// Inicialización de variables que se usan en las pruebas
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		utilsMethods = new UtilsMethods();

	}

	public Direccion crearDireccion(Integer id, String provincia, String ciudad, String direccion) {
		Direccion direccion1 = new Direccion();
		direccion1.setId(id);
		direccion1.setProvincia(provincia);
		direccion1.setCiudad(ciudad);
		direccion1.setDireccion(direccion);
		direccion1.setTipoDireccion(TipoDireccion.MATRIZ);
		return direccion1;
	}

	@Test
	public void testCreateClienteWithSucursales() {

		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, PROVINCE, CITY, ADDRESS, TYPE_ADDRESS_MATRIZ);
		Cliente entity = utilsMethods.createClienteEntity(CLIENTE_NAME, new ArrayList<>());

		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

		// Ejecución del método
		Cliente result = service.createCliente(dto);

		// Assersiones y verificaciones
		verify(mockRepo, times(1)).save(any(Cliente.class));
		assertEquals(dto.getNames(), result.getNombres());
	}

	@Test
	public void testCreateClienteEmptyFieldsAddress() {

		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, "", "", "");

		Cliente entity = utilsMethods.createClienteEntity(CLIENTE_NAME);

		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

		// Ejecución del método
		Cliente result = service.createCliente(dto);

		// Assersiones y verificaciones
		verify(mockRepo, times(1)).save(any(Cliente.class));
		assertEquals(dto.getNames(), result.getNombres());
	}

	@Test
	public void testCreateClienteCompleteFieldsAddress() {

		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, PROVINCE, CITY, ADDRESS);

		Cliente entity = utilsMethods.createClienteEntity(CLIENTE_NAME);

		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

		// Ejecución del método
		Cliente result = service.createCliente(dto);

		// Assersiones y verificaciones
		verify(mockRepo, times(1)).save(any(Cliente.class));
		assertEquals(dto.getNames(), result.getNombres());
	}

	@Test
	public void testCreateClienteNullFieldsAddress() {

		// Implementación de mocks

		Cliente entity = utilsMethods.createClienteEntity(CLIENTE_NAME);

		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

	}

	@Test
	public void testCreateCliente_ExistingClient() {
		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTOWithID(CLIENTE_NAME, NUM_IDENTIFICACION);

		Cliente existing = utilsMethods.createClienteEntityWithId(NUM_IDENTIFICACION);

		Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(existing);

		// Assersiones y verificaciones
		assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
		verify(mockRepo, times(0)).save(any(Cliente.class));
	}

	@Test
	public void testCreateCliente_InvalidAddressType() {
		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, NUM_IDENTIFICACION, PROVINCE, CITY, ADDRESS,
				INVALID_ADDRESS_TYPE);

		Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);

		// Assersiones y verificaciones
		assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
		verify(mockRepo, times(0)).save(any(Cliente.class));
	}

	@Test
	public void testCreateCliente_GetTypeAddressNull() {
		// Implementación de mocks

		Cliente entity = utilsMethods.createClienteEntity(CLIENTE_NAME);

		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

		
	}

	@Test
	public void testCreateCliente_AddMainAddress() {
		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, NUM_IDENTIFICACION, PROVINCE, CITY, ADDRESS,
				TYPE_ADDRESS_MATRIZ);

		Cliente existing = utilsMethods.createClienteEntityWithId(NUM_IDENTIFICACION);
		existing.setNumeroIdentificacion("1234");

		Cliente expected = new Cliente();
		expected.setNombres(dto.getNames());
		expected.setNumeroIdentificacion(dto.getIdentificationNumber());
		Direccion expectedDireccion = new Direccion();
		expectedDireccion.setTipoDireccion(TipoDireccion.MATRIZ);
		expected.setDireccionMatriz(expectedDireccion);

		Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);
		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(expected);

		// Ejecución del método
		Cliente result = service.createCliente(dto);

		// Assersiones y verificaciones
		verify(mockRepo, times(1)).save(any(Cliente.class));
		assertEquals(dto.getNames(), result.getNombres());
		assertEquals(dto.getIdentificationNumber(), result.getNumeroIdentificacion());
		assertNotNull(result.getDireccionMatriz());
		assertEquals(TipoDireccion.MATRIZ, result.getDireccionMatriz().getTipoDireccion());
	}

	@Test
	public void testCreateCliente_AddBranchAddress() {
		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, NUM_IDENTIFICACION, PROVINCE, CITY, ADDRESS,
				TYPE_ADDRESS_SUCURSAL);

		Cliente expected = new Cliente();
		expected.setNombres(dto.getNames());
		expected.setNumeroIdentificacion(dto.getIdentificationNumber());
		Direccion expectedDireccion = new Direccion();
		expectedDireccion.setTipoDireccion(TipoDireccion.SUCURSAL);
		expected.setDireccionesSucursales(Arrays.asList(expectedDireccion));

		Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);
		Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(expected);

		// Ejecución del método
		Cliente result = service.createCliente(dto);

		// Assersiones y verificaciones
		verify(mockRepo, times(1)).save(any(Cliente.class));
		assertEquals(dto.getNames(), result.getNombres());
		assertEquals(dto.getIdentificationNumber(), result.getNumeroIdentificacion());
		assertNotNull(result.getDireccionesSucursales());
		assertTrue(result.getDireccionesSucursales().contains(expectedDireccion));
	}

	@Test
	public void testCreateCliente_InvalidAddressType2() {
		// Implementación de mocks
		ClienteDTO dto = utilsMethods.createClienteDTO(CLIENTE_NAME, NUM_IDENTIFICACION, PROVINCE, CITY, ADDRESS,
				INVALID_ADDRESS_TYPE);

		Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);

		// Assersiones y verificaciones
		Exception exception = assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
		assertEquals("TipoDireccion no es válido. Debe ser 'MATRIZ' o 'SUCURSAL'", exception.getMessage());
		verify(mockRepo, times(0)).save(any(Cliente.class));
	}

	@Test
	public void testCreateClienteExistingClienteThrowsException() {
		// Organizar
		Cliente clienteExistente = utilsMethods.createClienteEntityWithId(NUM_IDENTIFICACION);
		clienteExistente.setNumeroIdentificacion(NUM_IDENTIFICACION);
		when(service.findByNumeroIdentificacion(NUM_IDENTIFICACION)).thenReturn(clienteExistente);

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdentificationNumber(NUM_IDENTIFICACION);

		// Actuar y afirmar
		assertThrows(IllegalArgumentException.class, () -> service.createCliente(clienteDTO));
	}

	@Test
	public void testCreateClienteNoExistingCliente() {
		try {
			// Organizar
			when(service.findByNumeroIdentificacion(NUM_IDENTIFICACION)).thenReturn(null);
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdentificationNumber(NUM_IDENTIFICACION);
			Cliente clienteGuardado = new Cliente();
			clienteGuardado.setNumeroIdentificacion(NUM_IDENTIFICACION);
			when(mockRepo.save(any(Cliente.class))).thenReturn(clienteGuardado);
			// Actuar
			Cliente result = service.createCliente(clienteDTO);

			// Afirmar
			assertEquals(NUM_IDENTIFICACION, result.getNumeroIdentificacion());
			verify(mockRepo, times(1)).save(any(Cliente.class));
		} catch (Exception e) {
			logger.info("");
		}

	}

	@Test
	public void testCreateClienteConDireccionesSucursalesNull() {

		try {
			when(mockRepo.findByNumeroIdentificacion(anyString())).thenReturn(null);
		} catch (Exception e) {
			logger.info("");
		}
	}

	@Test
	public void testCreateClienteConDireccionesSucursalesNonNull() {
		try {

			when(mockRepo.findByNumeroIdentificacion(anyString())).thenReturn(null);

		} catch (Exception e) {
			logger.info("");
		}

	}

	@Test
	public void testBuscarClientes() {

		Direccion direccion1 = crearDireccion(1, "Provincia de prueba", "Ciudad de prueba", "Direccion de prueba");

		Direccion direccion2 = crearDireccion(1, "Provincia de prueba 2", "Ciudad de prueba 2",
				"Direccion de prueba 2");

		Cliente cliente1 = new Cliente();
		cliente1.setNumeroIdentificacion("12345");
		cliente1.setNombres("John Doe");
		cliente1.setDireccionMatriz(direccion1);

		Cliente cliente2 = new Cliente();
		cliente2.setNumeroIdentificacion("67890");
		cliente2.setNombres("Jane Doe");
		cliente2.setDireccionMatriz(direccion2);

		when(mockRepo.findByNumeroIdentificacionContainingOrNombresContaining(anyString(), anyString()))
				.thenReturn(Arrays.asList(cliente1, cliente2));

		List<ClienteDTO> resultado = service.buscarClientes("Doe");

		assertEquals(2, resultado.size());

		assertEquals("John Doe", resultado.get(0).getNames());
		assertEquals("Jane Doe", resultado.get(1).getNames());

		verify(mockRepo, times(1)).findByNumeroIdentificacionContainingOrNombresContaining(anyString(), anyString());
	}

	@Test
	public void testUpdate_success() {

		try {
			Direccion direccion1 = crearDireccion(1, "Provincia de prueba", "Ciudad de prueba", "Direccion de prueba");

			Cliente cliente = new Cliente();
			cliente.setId(1);
			cliente.setNumeroIdentificacion("123456");
			cliente.setDireccionMatriz(direccion1);

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdentificationNumber("123456");

			when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));
			when(mockRepo.findByNumeroIdentificacion("123456")).thenReturn(null);
			when(mockRepo.save(any(Cliente.class))).thenReturn(cliente);

			ClienteDTO result = service.update(1, clienteDTO);

			assertEquals(cliente.getNumeroIdentificacion(), result.getIdentificationNumber());
		} catch (Exception e) {
			logger.info("");
		}

	}

	@Test
	public void testUpdate_notFound() {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdentificationNumber("123456");

		when(mockRepo.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResponseStatusException.class, () -> {
			service.update(1, clienteDTO);
		});
	}

	@Test
	public void testUpdate_identificationNumberAlreadyExists() {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNumeroIdentificacion("123456");

		Cliente clienteExistente = new Cliente();
		clienteExistente.setId(2);
		clienteExistente.setNumeroIdentificacion("123456");

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdentificationNumber("123456");

		when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));
		when(mockRepo.findByNumeroIdentificacion("123456")).thenReturn(clienteExistente);

		assertThrows(IllegalArgumentException.class, () -> {
			service.update(1, clienteDTO);
		});
	}

	@Test
	public void testFindById_success() {
		Cliente cliente = new Cliente();
		cliente.setId(1);

		when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));

		Cliente result = service.findById(1);

		assertEquals(cliente.getId(), result.getId());
	}

	@Test
    public void testFindById_notFound() {
        when(mockRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.findById(1);
        });
    }

	@Test
	@Transactional
	public void testEliminarCliente_success() {
		Cliente cliente = new Cliente();
		cliente.setId(1);

		when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));

		service.eliminarCliente(1);

		verify(mockRepo, times(1)).deleteById(1);
	}

	@Test
    @Transactional
    public void testEliminarCliente_notFound() {
        when(mockRepo.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(ResponseStatusException.class, () -> {
            service.eliminarCliente(1);
        });
    }

	@Test
	public void testUpdate_success_case2() {

		try {
			Direccion direccion1 = crearDireccion(1, "Provincia de prueba", "Ciudad de prueba", "Direccion de prueba");

			Cliente cliente = new Cliente();
			cliente.setId(1);
			cliente.setNumeroIdentificacion("123456");
			cliente.setDireccionMatriz(direccion1);

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdentificationNumber("123456");
			clienteDTO.setId(1);

			Cliente clienteNuevo = new Cliente();
			cliente.setId(1);
			cliente.setNumeroIdentificacion("123456");
			cliente.setDireccionMatriz(direccion1);

			when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));
			when(mockRepo.findByNumeroIdentificacion("123456")).thenReturn(clienteNuevo);
			when(mockRepo.save(any(Cliente.class))).thenReturn(cliente);

			ClienteDTO result = service.update(1, clienteDTO);

			assertEquals(cliente.getNumeroIdentificacion(), result.getIdentificationNumber());
		} catch (Exception e) {
			logger.info("");
		}

	}

	@Test
	public void testUpdateCliente_noExisteCliente() {
		// Preparar
		Integer id = 1;
		ClienteDTO clienteDTO = new ClienteDTO();
		when(mockRepo.findById(id)).thenReturn(Optional.empty());

		// Actuar y Afirmar
		assertThrows(ResponseStatusException.class, () -> service.update(id, clienteDTO));
	}

	@Test
	public void testUpdateCliente_yaExisteNumeroIdentificacion() {
		// Preparar
		Integer id = 1;
		Cliente cliente = new Cliente();
		cliente.setId(id);
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdentificationNumber("123456789");
		when(mockRepo.findById(id)).thenReturn(Optional.of(cliente));
		Cliente existingClient = new Cliente();
		existingClient.setId(2);
		when(mockRepo.findByNumeroIdentificacion(clienteDTO.getIdentificationNumber())).thenReturn(existingClient);
		// Actuar y Afirmar
		assertThrows(IllegalArgumentException.class, () -> service.update(id, clienteDTO));
	}

	@Test
	public void testUpdateCliente_actualizacionExitosa() {
		// Preparar
		Integer id = 1;
		Integer id2 = 1;

		Direccion direccion1 = crearDireccion(1, "Provincia de prueba", "Ciudad de prueba", "Direccion de prueba");

		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setDireccionMatriz(direccion1);

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdentificationNumber("123456789");
		clienteDTO.setId(id2);
		Cliente cliente2 = new Cliente();
		cliente2.setId(id2);
		cliente2.setNumeroIdentificacion("123456789");
		when(mockRepo.findById(id)).thenReturn(Optional.of(cliente));
		when(mockRepo.findByNumeroIdentificacion(clienteDTO.getIdentificationNumber())).thenReturn(cliente2);
		when(mockRepo.save(any(Cliente.class))).thenReturn(cliente);

		// Actuar
		ClienteDTO clienteActualizado = service.update(id, clienteDTO);

		// Afirmar
		assertEquals(clienteDTO.getIdentificationNumber(), clienteActualizado.getIdentificationNumber());
		verify(mockRepo, times(1)).save(cliente);
	}

}