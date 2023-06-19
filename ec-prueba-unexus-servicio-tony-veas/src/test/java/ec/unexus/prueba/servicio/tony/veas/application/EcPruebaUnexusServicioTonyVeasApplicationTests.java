package ec.unexus.prueba.servicio.tony.veas.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EcPruebaUnexusServicioTonyVeasApplicationTests {

	// Declaración de mocks
	@Mock
	private ApplicationContext context;
	@Mock
	private EcPruebaUnexusServicioTonyVeasApplication EcPruebaUnexusServicioTonyVeasApplication;

	// Inicialización mocks
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}

}
