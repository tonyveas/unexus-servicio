package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.utils.TipoDireccion;

public class DireccionDTOTest {

	@Test
	public void testDireccionDTO() {

		DireccionDTO direccionMatriz = new DireccionDTO();
		direccionMatriz.setId(1);
		direccionMatriz.setMainProvince("Provincia prueba matriz");
		direccionMatriz.setMainCity("Ciudad prueba matriz");
		direccionMatriz.setMainAddress("Dirección prueba matriz");
		direccionMatriz.setTypeAddress(TipoDireccion.MATRIZ.toString());
		direccionMatriz.toString();

		DireccionDTO direccionSucursal1 = new DireccionDTO();
		direccionSucursal1.setId(2);
		direccionSucursal1.setMainProvince("Provincia prueba sucursal 1");
		direccionSucursal1.setMainCity("Ciudad prueba sucursal 1");
		direccionSucursal1.setMainAddress("Dirección prueba sucursal 1");
		direccionSucursal1.setTypeAddress(TipoDireccion.SUCURSAL.toString());
		direccionSucursal1.toString();

		DireccionDTO direccionSucursal2 = new DireccionDTO();
		direccionSucursal2.setId(3);
		direccionSucursal2.setMainProvince("Provincia prueba sucursal 2");
		direccionSucursal2.setMainCity("Ciudad prueba sucursal 2");
		direccionSucursal2.setMainAddress("Dirección prueba sucursal 2");
		direccionSucursal2.setTypeAddress(TipoDireccion.SUCURSAL.toString());
		direccionSucursal2.toString();

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
