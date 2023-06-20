package ec.unexus.prueba.servicio.tony.veas.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

	// Creando variable usada en el test
	private Cliente cliente;

	// Inicialización de variables
	@BeforeEach
	void setUp() {
		cliente = new Cliente();
	}

	@Test
	void testSettersAndGetters() {
		// Instanciando y creando objetos de pruebas
		cliente.setId(1);
		cliente.setTipoIdentificacion("CI");
		cliente.setNumeroIdentificacion("1234567890");
		cliente.setNombres("John Doe");
		cliente.setCorreo("johndoe@email.com");
		cliente.setNumeroCelular("1234567890");
		Direccion direccion = new Direccion();
		cliente.setDireccionMatriz(direccion);
		cliente.setDireccionesSucursales(Collections.singletonList(direccion));
		cliente.toString();
		// Aserciones y verificaciones
		assertEquals("CI", cliente.getTipoIdentificacion());
		assertEquals(1, cliente.getId());
		assertEquals("1234567890", cliente.getNumeroIdentificacion());
		assertEquals("John Doe", cliente.getNombres());
		assertEquals("johndoe@email.com", cliente.getCorreo());
		assertEquals("1234567890", cliente.getNumeroCelular());
		assertEquals(direccion, cliente.getDireccionMatriz());
		assertEquals(1, cliente.getDireccionesSucursales().size());
		assertEquals(direccion, cliente.getDireccionesSucursales().get(0));
	}

}
