package ec.unexus.prueba.servicio.tony.veas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;
import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;
import jakarta.transaction.Transactional;

public class DireccionServiceTest {

	// Declaramos el mock de ClienteRepository
	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private DireccionRepository direccionRepository;

	// Usamos @InjectMocks para que Mockito cree una instancia de DireccionService
	// e inyecte el mock de ClienteRepository y DireccionRepository en él.
	@InjectMocks
	private DireccionService direccionService;

	// Inicialización de variables que se usan en las pruebas
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	@Transactional
	public void testAgregarDireccion_Matriz_success() {
		// Instanciando objetos de pruebas
		Cliente cliente = new Cliente();
		cliente.setId(1);
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());
		Direccion nuevaDireccion = new Direccion();
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		when(direccionRepository.save(any(Direccion.class))).thenReturn(nuevaDireccion);
		Direccion resultado = direccionService.agregarDireccion(1, direccionDTO);
		// Aserciones y verificaciones
		assertEquals(nuevaDireccion, resultado);
		verify(clienteRepository, times(1)).save(cliente);
	}

	@Test
	@Transactional
	public void testAgregarDireccion_notFound() {
		// Instanciando objetos de pruebas
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.empty());
		// Aserciones y verificaciones
		assertThrows(ResponseStatusException.class, () -> {
			direccionService.agregarDireccion(1, direccionDTO);
		});
	}

	@Test
	@Transactional
	public void testAgregarDireccion_SegundaMatriz() {
		// Instanciando objetos de pruebas
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setDireccionMatriz(new Direccion());
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		// Aserciones y verificaciones
		assertThrows(ResponseStatusException.class, () -> {
			direccionService.agregarDireccion(1, direccionDTO);
		});
	}

	@Test
	public void testCreateFromDTO_TipoDireccionNotProvided() {
		// Instanciando objetos de pruebas
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setProvince("Provincia");
		direccionDTO.setCity("Ciudad");
		direccionDTO.setAddress("Direccion");
		Direccion direccion = direccionService.createFromDTO(direccionDTO);
		// Aserciones y verificaciones
		assertEquals(TipoDireccion.MATRIZ, direccion.getTipoDireccion());
		assertEquals("Provincia", direccion.getProvincia());
		assertEquals("Ciudad", direccion.getCiudad());
		assertEquals("Direccion", direccion.getDireccion());
	}

	@Test
	public void testCreateFromDTO_TipoDireccionProvided() {
		// Instanciando objetos de pruebas
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setProvince("Provincia");
		direccionDTO.setCity("Ciudad");
		direccionDTO.setAddress("Direccion");
		direccionDTO.setTypeAddress(TipoDireccion.SUCURSAL.name());
		Direccion direccion = direccionService.createFromDTO(direccionDTO);
		// Aserciones y verificaciones
		assertEquals(TipoDireccion.SUCURSAL, direccion.getTipoDireccion());
		assertEquals("Provincia", direccion.getProvincia());
		assertEquals("Ciudad", direccion.getCiudad());
		assertEquals("Direccion", direccion.getDireccion());
	}

	@Test
	public void testGetDireccionesCliente_conDirecciones() {
		// Instanciando objetos de pruebas
		Cliente cliente = new Cliente();
		cliente.setId(1);
		Direccion direccion1 = new Direccion();
		direccion1.setTipoDireccion(TipoDireccion.MATRIZ);
		direccion1.setProvincia("Provincia matriz");
		direccion1.setCiudad("Ciudad matriz");
		direccion1.setDireccion("Direccion matriz");
		Direccion direccion2 = new Direccion();
		direccion2.setTipoDireccion(TipoDireccion.SUCURSAL);
		direccion2.setProvincia("Provincia 2");
		direccion2.setCiudad("Ciudad 2");
		direccion2.setDireccion("Direccion 2");
		Direccion direccion3 = new Direccion();
		direccion3.setTipoDireccion(TipoDireccion.SUCURSAL);
		direccion3.setProvincia("Provincia 3");
		direccion3.setCiudad("Ciudad 3");
		direccion3.setDireccion("Direccion 3");
		cliente.setDireccionesSucursales(Arrays.asList(direccion1, direccion2));
		cliente.setDireccionMatriz(direccion3);
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		ClienteDireccionesDTO resultado = direccionService.getDireccionesCliente(1);
		// Aserciones y verificaciones
		assertEquals(direccion3, resultado.getDireccionMatriz());
		assertEquals(1, resultado.getDireccionesSucursales().size());
	}

	@Test
    public void testGetDireccionesCliente_noExisteCliente() {
		// Configurando comportamiento de los mocks
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());
		// Aserciones y verificaciones
        assertThrows(ResponseStatusException.class, () -> {
            direccionService.getDireccionesCliente(1);
        });
    }

	@Test
	public void testSaveDireccion() {
		// Instanciando objetos de pruebas
		Direccion direccion = new Direccion();
		direccion.setId(1);
		direccion.setProvincia("provincia");
		direccion.setCiudad("ciudad");
		direccion.setDireccion("direccion");
		Cliente cliente = new Cliente();
		cliente.setNombres("Juan Lindao");
		cliente.setCorreo("juan.lindao@mail.es");
		cliente.setNumeroCelular("098765432");
		cliente.setNumeroIdentificacion("9012987654");
		cliente.setTipoIdentificacion(TipoDireccion.MATRIZ.toString());
		cliente.setDireccionesSucursales(new ArrayList<Direccion>());
		direccion.setCliente(cliente);
		// Configurando comportamiento de los mocks
		when(direccionRepository.save(any(Direccion.class))).thenReturn(direccion);
		Direccion savedDireccion = direccionService.save(direccion);
		// Aserciones y verificaciones
		verify(direccionRepository, times(1)).save(direccion);
		assertEquals(direccion, savedDireccion);
	}
	
	@Test
	@Transactional
	public void testAgregarDireccion_Matriz_success_case_sucursal_default() {
		// Instanciando objetos de pruebas
		Cliente cliente = new Cliente();
		cliente.setId(1);
		DireccionDTO direccionDTO = new DireccionDTO();
		Direccion nuevaDireccion = new Direccion();
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		when(direccionRepository.save(any(Direccion.class))).thenReturn(nuevaDireccion);
		Direccion resultado = direccionService.agregarDireccion(1, direccionDTO);
		// Aserciones y verificaciones
		assertEquals(nuevaDireccion, resultado);
	}
	
	@Test
	@Transactional
	public void testAgregarDireccion_Matriz_success_case_sucursal() {
		// Instanciando objetos de pruebas
		Cliente cliente = new Cliente();
		cliente.setId(1);
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setTypeAddress(TipoDireccion.SUCURSAL.name());
		Direccion nuevaDireccion = new Direccion();
		// Configurando comportamiento de los mocks
		when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
		when(direccionRepository.save(any(Direccion.class))).thenReturn(nuevaDireccion);
		Direccion resultado = direccionService.agregarDireccion(1, direccionDTO);
		// Aserciones y verificaciones
		assertEquals(nuevaDireccion, resultado);
	}
	
}
