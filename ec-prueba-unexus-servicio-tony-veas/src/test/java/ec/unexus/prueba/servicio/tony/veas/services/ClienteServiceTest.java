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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import jakarta.transaction.Transactional;


public class ClienteServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Declaramos el mock de ClienteRepository
    @Mock
    private ClienteRepository mockRepo;

    // Usamos @InjectMocks para que Mockito cree una instancia de ClienteService
    // e inyecte el mock de ClienteRepository en él.
    @InjectMocks
    private ClienteService service;

    // Inicialización de variables que se usan en las pruebas
    @BeforeEach
    public void setup() {
    	mockRepo = Mockito.mock(ClienteRepository.class);
    	service = new ClienteService(mockRepo);
    }

    @Test
    public void testCreateClienteWithSucursales() {
    	
    	// Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setMainProvince("Provincia");
        dto.setMainCity("Ciudad");
        dto.setMainAddress("Address");
        dto.setTypeAddress(TipoDireccion.MATRIZ.toString());
        
        Cliente entity = new Cliente();
        entity.setNombres("Tony Veas");
        entity.setDireccionesSucursales(new ArrayList<>());
        
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
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setMainProvince("");
        dto.setMainCity("");
        dto.setMainAddress("");
        
        Cliente entity = new Cliente();
        entity.setNombres("Tony Veas");
        
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
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setMainProvince("Provincia");
        dto.setMainCity("Ciudad");
        dto.setMainAddress("Address");
        
        Cliente entity = new Cliente();
        entity.setNombres("Tony Veas");
        
        Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

        // Ejecución del método
        Cliente result = service.createCliente(dto);

        // Assersiones y verificaciones
        verify(mockRepo, times(1)).save(any(Cliente.class));
        assertEquals(dto.getNames(), result.getNombres());
    }
    
    @Test
    public void testCreateClienteNullFieldsAddress() {
    	
    	try {
    	// Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setMainProvince(null);
        dto.setMainCity(null);
        dto.setMainAddress(null);
        
        Cliente entity = new Cliente();
        entity.setNombres("Tony Veas");
        
        Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);

        // Ejecución del método
        Cliente result = service.createCliente(dto);

        // Assersiones y verificaciones
        verify(mockRepo, times(1)).save(any(Cliente.class));
        assertEquals(dto.getNames(), result.getNombres());
    	} catch(Exception e) {
    		logger.info(e.getMessage());
    	}
    	
    }
    
    @Test
    public void testCreateCliente_ExistingClient() {
        // Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");

        Cliente existing = new Cliente();
        existing.setNumeroIdentificacion("1234");

        Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(existing);

        // Assersiones y verificaciones
        assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
        verify(mockRepo, times(0)).save(any(Cliente.class));
    }

    @Test
    public void testCreateCliente_InvalidAddressType() {
        // Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");
        dto.setMainProvince("SomeProvince");
        dto.setMainCity("SomeCity");
        dto.setMainAddress("SomeAddress");
        dto.setTypeAddress("INVALID_ADDRESS_TYPE");

        Cliente existing = new Cliente();
        existing.setNumeroIdentificacion("1234");

        Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);

        // Assersiones y verificaciones
        assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
        verify(mockRepo, times(0)).save(any(Cliente.class));
    }

    @Test
    public void testCreateCliente_GetTypeAddressNull() {
        // Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");
        dto.setMainProvince("SomeProvince");
        dto.setMainCity("SomeCity");
        dto.setMainAddress("SomeAddress");

        Cliente existing = new Cliente();
        existing.setNumeroIdentificacion("1234");
        
        Cliente entity = new Cliente();
        entity.setNombres("Tony Veas");
        
        Mockito.when(mockRepo.save(any(Cliente.class))).thenReturn(entity);
        
        // Ejecución del método
        Cliente result = service.createCliente(dto);
        
        // Assersiones y verificaciones
        verify(mockRepo, times(1)).save(any(Cliente.class));
        assertEquals(dto.getNames(), result.getNombres());
    }
    

    @Test
    public void testCreateCliente_AddMainAddress() {
        // Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");
        dto.setMainProvince("SomeProvince");
        dto.setMainCity("SomeCity");
        dto.setMainAddress("SomeAddress");
        dto.setTypeAddress("MATRIZ");

        Cliente existing = new Cliente();
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
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");
        dto.setMainProvince("SomeProvince");
        dto.setMainCity("SomeCity");
        dto.setMainAddress("SomeAddress");
        dto.setTypeAddress("SUCURSAL");

        Cliente existing = new Cliente();
        existing.setNumeroIdentificacion("1234");

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
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        dto.setIdentificationNumber("1234");
        dto.setMainProvince("SomeProvince");
        dto.setMainCity("SomeCity");
        dto.setMainAddress("SomeAddress");
        dto.setTypeAddress("INVALIDO");

        Mockito.when(mockRepo.findByNumeroIdentificacion(dto.getIdentificationNumber())).thenReturn(null);

        // Assersiones y verificaciones
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.createCliente(dto));
        assertEquals("TipoDireccion no es válido. Debe ser 'MATRIZ' o 'SUCURSAL'", exception.getMessage());
        verify(mockRepo, times(0)).save(any(Cliente.class));
    }
	

    @Test
    public void testCreateClienteExistingClienteThrowsException() {
        // Organizar
        String numeroIdentificacion = "123";
        Cliente clienteExistente = new Cliente();
        clienteExistente.setNumeroIdentificacion(numeroIdentificacion);
        when(service.findByNumeroIdentificacion(numeroIdentificacion)).thenReturn(clienteExistente);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber(numeroIdentificacion);

        // Actuar y afirmar
        assertThrows(IllegalArgumentException.class, () -> service.createCliente(clienteDTO));
    }

    @Test
    public void testCreateClienteNoExistingCliente() {
        // Organizar
        String numeroIdentificacion = "123";
        when(service.findByNumeroIdentificacion(numeroIdentificacion)).thenReturn(null);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber(numeroIdentificacion);
        
        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setNumeroIdentificacion(numeroIdentificacion);
        
        when(mockRepo.save(any(Cliente.class))).thenReturn(clienteGuardado);

        // Actuar
        Cliente result = service.createCliente(clienteDTO);

        // Afirmar
        assertEquals(numeroIdentificacion, result.getNumeroIdentificacion());
        verify(mockRepo, times(1)).save(any(Cliente.class));
    }
    
    // @Test
    public void testCreateClienteNonExistingClienteReturnsCliente() {
        // Organizar
        String numeroIdentificacion = "123";
        when(mockRepo.findByNumeroIdentificacion(numeroIdentificacion)).thenReturn(null);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber(numeroIdentificacion);
        
        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setNumeroIdentificacion(numeroIdentificacion);
        
        when(mockRepo.save(any(Cliente.class))).thenReturn(clienteGuardado);

        // Actuar
        Cliente result = service.createCliente(clienteDTO);

        // Afirmar
        assertEquals(numeroIdentificacion, result.getNumeroIdentificacion());
        verify(mockRepo, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testCreateClienteConDireccionesSucursalesNull() {
        // Configura el objeto clienteDTO para tener algún valor para MainProvince, MainCity y MainAddress
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber("12345");
        clienteDTO.setMainProvince("Some Province");
        clienteDTO.setMainCity("Some City");
        clienteDTO.setMainAddress("Some Address");
        clienteDTO.setTypeAddress("MATRIZ");  // o "SUCURSAL", según tus necesidades

        // Configura el mock para devolver null cuando se llame a findByNumeroIdentificacion
        when(mockRepo.findByNumeroIdentificacion(anyString())).thenReturn(null);

        // Llama a tu método con el objeto clienteDTO
        Cliente nuevoCliente = service.createCliente(clienteDTO);

        // Verifica que el método save se ha llamado una vez
        verify(mockRepo, times(1)).save(any(Cliente.class));

        // Añade más verificaciones según tus necesidades...
    }

    @Test
    public void testCreateClienteConDireccionesSucursalesNonNull() {
        // Configura el objeto clienteDTO para tener algún valor para MainProvince, MainCity y MainAddress
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber("12345");
        clienteDTO.setMainProvince("Some Province");
        clienteDTO.setMainCity("Some City");
        clienteDTO.setMainAddress("Some Address");
        clienteDTO.setTypeAddress("SUCURSAL");  // este caso debe cubrir la condición del else

        // Configura el mock para devolver null cuando se llame a findByNumeroIdentificacion
        when(mockRepo.findByNumeroIdentificacion(anyString())).thenReturn(null);

        // Llama a tu método con el objeto clienteDTO
        Cliente nuevoCliente = service.createCliente(clienteDTO);

        // Verifica que el método save se ha llamado una vez
        verify(mockRepo, times(1)).save(any(Cliente.class));

        // Añade más verificaciones según tus necesidades...
    }

    @Test
    public void testBuscarClientes() {
        // Crear algunos clientes de prueba
    	
    	Direccion direccion1 = new Direccion();
    	direccion1.setProvincia("Provincia de prueba");
    	direccion1.setCiudad("Ciudad de prueba");
    	direccion1.setDireccion("Direccion de prueba");
    	direccion1.setTipoDireccion(TipoDireccion.MATRIZ);
    	direccion1.setId(1);
    	
    	Direccion direccion2 = new Direccion();
    	direccion2.setProvincia("Provincia de prueba 2");
    	direccion2.setCiudad("Ciudad de prueba 2");
    	direccion2.setDireccion("Direccion de prueba 2");
    	direccion2.setTipoDireccion(TipoDireccion.MATRIZ);
    	direccion2.setId(1);
    	
    	Cliente cliente1 = new Cliente();
    	cliente1.setNumeroIdentificacion("12345");
    	cliente1.setNombres("John Doe");
    	cliente1.setDireccionMatriz(direccion1);
    	

        Cliente cliente2 = new Cliente();
        cliente2.setNumeroIdentificacion("67890");
        cliente2.setNombres("Jane Doe");
    	cliente2.setDireccionMatriz(direccion2);


        // Devuelve la lista de clientes cuando se llama al método mockeado
        when(mockRepo.findByNumeroIdentificacionContainingOrNombresContaining(anyString(), anyString())).thenReturn(Arrays.asList(cliente1, cliente2));

        // Llama al método de prueba con una cadena de búsqueda
        List<ClienteDTO> resultado = service.buscarClientes("Doe");

        // Verifica que la lista devuelta tiene el tamaño correcto
        assertEquals(2, resultado.size());

        // Verifica que los nombres de los clientes devueltos son correctos
        assertEquals("John Doe", resultado.get(0).getNames());
        assertEquals("Jane Doe", resultado.get(1).getNames());

        // Verifica que el método mockeado se llamó una vez
        verify(mockRepo, times(1)).findByNumeroIdentificacionContainingOrNombresContaining(anyString(), anyString());
    }
    
    
    @Test
    public void testUpdate_success() {
    	
    	Direccion direccion1 = new Direccion();
    	direccion1.setProvincia("Provincia de prueba");
    	direccion1.setCiudad("Ciudad de prueba");
    	direccion1.setDireccion("Direccion de prueba");
    	direccion1.setTipoDireccion(TipoDireccion.MATRIZ);
    	direccion1.setId(1);
    	
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNumeroIdentificacion("123456");
        cliente.setDireccionMatriz(direccion1);
        // Setear el resto de las propiedades

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber("123456");
        // Setear el resto de las propiedades

        when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));
        when(mockRepo.findByNumeroIdentificacion("123456")).thenReturn(null);
        when(mockRepo.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO result = service.update(1, clienteDTO);

        // Asegúrate de que las propiedades se establezcan correctamente
        assertEquals(cliente.getNumeroIdentificacion(), result.getIdentificationNumber());
        // Comprueba el resto de las propiedades
    }

    
    @Test
    public void testUpdate_notFound() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber("123456");
        // Setear el resto de las propiedades

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
        // Setear el resto de las propiedades

        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(2);
        clienteExistente.setNumeroIdentificacion("123456");
        // Setear el resto de las propiedades

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationNumber("123456");
        // Setear el resto de las propiedades

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
        // Setear el resto de las propiedades

        when(mockRepo.findById(1)).thenReturn(Optional.of(cliente));

        Cliente result = service.findById(1);

        // Asegúrate de que las propiedades se establezcan correctamente
        assertEquals(cliente.getId(), result.getId());
        // Comprueba el resto de las propiedades
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
        // Establecer las demás propiedades

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
	
    
}