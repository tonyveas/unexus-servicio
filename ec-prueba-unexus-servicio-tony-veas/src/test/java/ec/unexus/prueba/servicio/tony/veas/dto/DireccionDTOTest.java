package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.utils.UtilsMethods;

public class DireccionDTOTest {

	// Creando variable usada en el test
	private UtilsMethods utilsMethods;

	// Inicialización de variables
	@BeforeEach
	public void setup() {
		utilsMethods = new UtilsMethods();
	}

	@Test
	public void testDireccionDTO() {
		// Instanciando y creando objetos de pruebas
		DireccionDTO direccionMatriz = utilsMethods.crearDireccionDTO(1, "Provincia prueba matriz", 
																	  "Ciudad prueba matriz", "Dirección prueba matriz",
																	  TipoDireccion.MATRIZ.toString());
		DireccionDTO direccionSucursal1 = utilsMethods.crearDireccionDTO(2, "Provincia prueba sucursal 1",
										                                 "Ciudad prueba sucursal 1", "Dirección prueba sucursal 1",
										                                 TipoDireccion.SUCURSAL.toString());
		DireccionDTO direccionSucursal2 = utilsMethods.crearDireccionDTO(3, "Provincia prueba sucursal 2",
														                 "Ciudad prueba sucursal 2", "Dirección prueba sucursal 2",
														                 TipoDireccion.SUCURSAL.toString());
		direccionMatriz.toString();
		direccionSucursal1.toString();
		direccionSucursal2.toString();
		// Aserciones y verificaciones
		assertEquals("Provincia prueba matriz", direccionMatriz.getMainProvince());
		assertEquals(1, direccionMatriz.getId());
		assertEquals("Ciudad prueba matriz", direccionMatriz.getMainCity());
		assertEquals("Dirección prueba matriz", direccionMatriz.getMainAddress());
		assertEquals(TipoDireccion.MATRIZ.toString(), direccionMatriz.getTypeAddress());
		assertEquals("Provincia prueba sucursal 1", direccionSucursal1.getMainProvince());
		assertEquals(2, direccionSucursal1.getId());
		assertEquals("Ciudad prueba sucursal 1", direccionSucursal1.getMainCity());
		assertEquals("Dirección prueba sucursal 1", direccionSucursal1.getMainAddress());
		assertEquals(TipoDireccion.SUCURSAL.toString(), direccionSucursal1.getTypeAddress());
		assertEquals("Provincia prueba sucursal 2", direccionSucursal2.getMainProvince());
		assertEquals(3, direccionSucursal2.getId());
		assertEquals("Ciudad prueba sucursal 2", direccionSucursal2.getMainCity());
		assertEquals("Dirección prueba sucursal 2", direccionSucursal2.getMainAddress());
		assertEquals(TipoDireccion.SUCURSAL.toString(), direccionSucursal2.getTypeAddress());

	}

}
