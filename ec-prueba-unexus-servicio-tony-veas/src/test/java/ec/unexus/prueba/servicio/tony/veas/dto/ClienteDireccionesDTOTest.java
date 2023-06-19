package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;

public class ClienteDireccionesDTOTest {

	@Test
	public void testClienteDireccionesDTO() {
		ClienteDireccionesDTO clienteDireccionesDTO = new ClienteDireccionesDTO();
		List<Direccion> direccionesSucursales = new ArrayList<>();

		Direccion direccionMatriz = new Direccion();
		direccionMatriz.setId(1);
		direccionMatriz.setProvincia("Provincia prueba matriz");
		direccionMatriz.setCiudad("Ciudad prueba matriz");
		direccionMatriz.setDireccion("Dirección prueba matriz");

		Direccion direccionSucursal1 = new Direccion();
		direccionSucursal1.setId(2);
		direccionSucursal1.setProvincia("Provincia prueba sucursal 1");
		direccionSucursal1.setCiudad("Ciudad prueba sucursal 1");
		direccionSucursal1.setDireccion("Dirección prueba sucursal 1");

		Direccion direccionSucursal2 = new Direccion();
		direccionSucursal2.setId(3);
		direccionSucursal2.setProvincia("Provincia prueba sucursal 2");
		direccionSucursal2.setCiudad("Ciudad prueba sucursal 2");
		direccionSucursal2.setDireccion("Dirección prueba sucursal 2");

		direccionesSucursales.add(direccionSucursal1);
		direccionesSucursales.add(direccionSucursal2);

		clienteDireccionesDTO.setDireccionesSucursales(direccionesSucursales);
		clienteDireccionesDTO.setDireccionMatriz(direccionMatriz);
		clienteDireccionesDTO.toString();
		assertEquals(direccionesSucursales, clienteDireccionesDTO.getDireccionesSucursales());
		assertEquals(direccionMatriz, clienteDireccionesDTO.getDireccionMatriz());

	}

}
