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

	@Mock
	private ClienteRepository clienteRepo;

	@Mock
	private DireccionRepository direccionRepo;

	@InjectMocks
	private DataSeeder dataSeeder;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void testRun() throws Exception {
        when(clienteRepo.count()).thenReturn(0L);
        dataSeeder.run();
        verify(direccionRepo, times(5)).save(any());
    }

	@Test
    public void testNoRun() throws Exception {
        when(clienteRepo.count()).thenReturn(9L);
        dataSeeder.run();
    }

}
