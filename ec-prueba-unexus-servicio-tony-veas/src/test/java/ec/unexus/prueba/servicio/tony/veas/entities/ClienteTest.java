package ec.unexus.prueba.servicio.tony.veas.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

	private Cliente cliente;

	@BeforeEach
	void setUp() {
		cliente = new Cliente();
	}

	@Test
	void testSettersAndGetters() {
		cliente.setId(1);
		assertEquals(1, cliente.getId());

		cliente.setTipoIdentificacion("CI");
		assertEquals("CI", cliente.getTipoIdentificacion());

		cliente.setNumeroIdentificacion("1234567890");
		assertEquals("1234567890", cliente.getNumeroIdentificacion());

		cliente.setNombres("John Doe");
		assertEquals("John Doe", cliente.getNombres());

		cliente.setCorreo("johndoe@email.com");
		assertEquals("johndoe@email.com", cliente.getCorreo());

		cliente.setNumeroCelular("1234567890");
		assertEquals("1234567890", cliente.getNumeroCelular());

		Direccion direccion = new Direccion();
		cliente.setDireccionMatriz(direccion);
		assertEquals(direccion, cliente.getDireccionMatriz());

		cliente.setDireccionesSucursales(Collections.singletonList(direccion));
		assertEquals(1, cliente.getDireccionesSucursales().size());
		assertEquals(direccion, cliente.getDireccionesSucursales().get(0));
		cliente.toString();
	}

}
