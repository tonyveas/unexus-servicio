package ec.unexus.prueba.servicio.tony.veas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class EcPruebaUnexusServicioTonyVeasApplicationTests {

	@Mock
	private ApplicationContext context;

	@Mock
	private EcPruebaUnexusServicioTonyVeasApplication EcPruebaUnexusServicioTonyVeasApplication;

	// Inicialización de variables que se usan en las pruebas
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}

}
