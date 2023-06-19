package ec.unexus.prueba.servicio.tony.veas.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

public class DireccionTest {

	private Direccion direccion;

	@BeforeEach
	void setUp() {
		direccion = new Direccion();
	}

	@Test
	void testSettersAndGetters() {
		direccion.setId(1);
		assertEquals(1, direccion.getId());

		direccion.setProvincia("Pichincha");
		assertEquals("Pichincha", direccion.getProvincia());

		direccion.setCiudad("Quito");
		assertEquals("Quito", direccion.getCiudad());

		direccion.setDireccion("Av. Amazonas");
		assertEquals("Av. Amazonas", direccion.getDireccion());

		direccion.setTipoDireccion(TipoDireccion.MATRIZ);
		assertEquals(TipoDireccion.MATRIZ, direccion.getTipoDireccion());

		Cliente cliente = new Cliente();
		direccion.setCliente(cliente);
		assertEquals(cliente, direccion.getCliente());
		direccion.toString();
	}

}
