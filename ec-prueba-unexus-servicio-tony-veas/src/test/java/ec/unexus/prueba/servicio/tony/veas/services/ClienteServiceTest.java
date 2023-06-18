package ec.unexus.prueba.servicio.tony.veas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;


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
    public void testCreateCliente() {
    	
        // Implementación de mocks
        ClienteDTO dto = new ClienteDTO();
        dto.setNames("Tony Veas");
        
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

    
}