package ec.unexus.prueba.servicio.tony.veas.seeders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import ec.unexus.prueba.servicio.tony.veas.repositories.DireccionRepository;

public class DataSeederTest {

	// Declaramos los mocks de ClienteRepository y DireccionRepository
	@Mock
	private ClienteRepository clienteRepo;
	@Mock
	private DireccionRepository direccionRepo;

	/*
	 * Usamos @InjectMocks para que Mockito cree una instancia de
	 * DataSeeder
	 * e inyecte el mock de ClienteRepository  y DireccionRepository en él.
	 */
	@InjectMocks
	private DataSeeder dataSeeder;

	// Inicialización de variables que se usan en las pruebas
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void testRun() throws Exception {
		// Configurando comportamiento del mock
        when(clienteRepo.count()).thenReturn(0L);
        // Ejecución del método
        dataSeeder.run();
        // Verificaciones 
        verify(direccionRepo, times(5)).save(any());
    }

	@Test
    public void testNoRun() throws Exception {
		// Configurando comportamiendo del mocl
        when(clienteRepo.count()).thenReturn(9L);
        // Ejecución del método
        dataSeeder.run();
    }

}
