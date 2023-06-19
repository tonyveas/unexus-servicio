package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.utils.UtilsMethods;

public class ClienteDTOTest {

	// Creando variable usada en el test
	private UtilsMethods utilsMethods;

	// Inicialización de variables
	@BeforeEach
	public void setup() {
		utilsMethods = new UtilsMethods();
	}

	@Test
	public void testClienteDTO() {
		// Instanciando y creando objetos de pruebas
		ClienteDTO cliente = utilsMethods.createClienteDTO(1, "Tony Veas", "Cédula", 
														   "1234567890", "tony.veas@example.com", 
														   "0999999999", "Pichincha", "Quito",
														   "Av. Los Shyris", "MATRIZ");
		cliente.toString();
		// Aserciones y verificaciones
		assertEquals(1, cliente.getId());
		assertEquals("Cédula", cliente.getIdentificationType());
		assertEquals("1234567890", cliente.getIdentificationNumber());
		assertEquals("Tony Veas", cliente.getNames());
		assertEquals("tony.veas@example.com", cliente.getEmail());
		assertEquals("0999999999", cliente.getCellphone());
		assertEquals("Pichincha", cliente.getMainProvince());
		assertEquals("Quito", cliente.getMainCity());
		assertEquals("Av. Los Shyris", cliente.getMainAddress());
		assertEquals("MATRIZ", cliente.getTypeAddress());
	}
}
