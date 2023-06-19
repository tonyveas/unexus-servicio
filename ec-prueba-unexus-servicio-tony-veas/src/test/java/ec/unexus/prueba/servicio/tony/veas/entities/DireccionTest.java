package ec.unexus.prueba.servicio.tony.veas.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

public class DireccionTest {

	// Creando variable usada en el test
	private Direccion direccion;

	// Inicialización de variables
	@BeforeEach
	void setUp() {
		direccion = new Direccion();
	}

	@Test
	void testSettersAndGetters() {
		// Instanciando y creando objetos de pruebas
		direccion.setId(1);
		direccion.setProvincia("Pichincha");
		direccion.setCiudad("Quito");
		direccion.setDireccion("Av. Amazonas");
		direccion.setTipoDireccion(TipoDireccion.MATRIZ);
		Cliente cliente = new Cliente();
		direccion.setCliente(cliente);
		direccion.toString();
		
		// Aserciones y verificaciones
		assertEquals(1, direccion.getId());
		assertEquals("Pichincha", direccion.getProvincia());
		assertEquals("Quito", direccion.getCiudad());
		assertEquals("Av. Amazonas", direccion.getDireccion());
		assertEquals(TipoDireccion.MATRIZ, direccion.getTipoDireccion());
		assertEquals(cliente, direccion.getCliente());
	}

}
