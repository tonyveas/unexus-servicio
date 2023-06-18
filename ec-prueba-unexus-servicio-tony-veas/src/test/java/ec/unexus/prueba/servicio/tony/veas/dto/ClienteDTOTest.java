package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteDTOTest {
	@Test
	public void testClienteDTO() {
		ClienteDTO cliente = new ClienteDTO();

		cliente.setId(1);
		cliente.setIdentificationType("Cedula");
		cliente.setIdentificationNumber("001-001001-1");
		cliente.setNames("Tony Veas");
		cliente.setEmail("tony.veas@example.com");
		cliente.setCellphone("0999999999");
		cliente.setMainProvince("Pichincha");
		cliente.setMainCity("Quito");
		cliente.setMainAddress("Av. Los Shyris");
		cliente.setTypeAddress("Residencial");
		cliente.toString();
		assertEquals(1, cliente.getId());
		assertEquals("Cedula", cliente.getIdentificationType());
		assertEquals("001-001001-1", cliente.getIdentificationNumber());
		assertEquals("Tony Veas", cliente.getNames());
		assertEquals("tony.veas@example.com", cliente.getEmail());
		assertEquals("0999999999", cliente.getCellphone());
		assertEquals("Pichincha", cliente.getMainProvince());
		assertEquals("Quito", cliente.getMainCity());
		assertEquals("Av. Los Shyris", cliente.getMainAddress());
		assertEquals("Residencial", cliente.getTypeAddress());
	}
}
