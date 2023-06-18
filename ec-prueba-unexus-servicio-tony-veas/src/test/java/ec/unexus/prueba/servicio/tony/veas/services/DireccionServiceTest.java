package ec.unexus.prueba.servicio.tony.veas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;
import jakarta.transaction.Transactional;

public class DireccionServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Declaramos el mock de ClienteRepository
    @Mock
    private ClienteRepository clienteRepository;
    
    @Mock
    private DireccionRepository direccionRepository;

    // Usamos @InjectMocks para que Mockito cree una instancia de ClienteService
    // e inyecte el mock de ClienteRepository en él.
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
        Cliente cliente = new Cliente();
        cliente.setId(1);
        // establecer las demás propiedades

        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());
        // establecer las demás propiedades

        Direccion nuevaDireccion = new Direccion();
        // establecer las propiedades

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(direccionRepository.save(any(Direccion.class))).thenReturn(nuevaDireccion);

        Direccion resultado = direccionService.agregarDireccion(1, direccionDTO);

        assertEquals(nuevaDireccion, resultado);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    @Transactional
    public void testAgregarDireccion_notFound() {
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());

        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
        	direccionService.agregarDireccion(1, direccionDTO);
        });
    }

    @Test
    @Transactional
    public void testAgregarDireccion_SegundaMatriz() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setDireccionMatriz(new Direccion());
        
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setTypeAddress(TipoDireccion.MATRIZ.name());

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        assertThrows(ResponseStatusException.class, () -> {
        	direccionService.agregarDireccion(1, direccionDTO);
        });
    }

    
}
