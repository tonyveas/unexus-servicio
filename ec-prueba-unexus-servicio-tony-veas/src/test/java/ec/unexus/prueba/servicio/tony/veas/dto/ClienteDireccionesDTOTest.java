package ec.unexus.prueba.servicio.tony.veas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.utils.UtilsMethods;

public class ClienteDireccionesDTOTest {

	// Creando variable usada en el test
	private UtilsMethods utilsMethods;

	// Inicialización de variables
	@BeforeEach
	public void setup() {
		utilsMethods = new UtilsMethods();
	}

	@Test
	public void testClienteDireccionesDTO() {

		// Instanciando y creando objetos de pruebas
		ClienteDireccionesDTO clienteDireccionesDTO = new ClienteDireccionesDTO();
		List<Direccion> direccionesSucursales = new ArrayList<>();
		Direccion direccionMatriz = utilsMethods.crearDireccion(1, "Provincia prueba matriz", 
																"Ciudad prueba matriz", 
																"Dirección prueba matriz");
		Direccion direccionSucursal1 = utilsMethods.crearDireccion(2, "Provincia prueba sucursal 1", 
																   "Ciudad prueba sucursal 1", 
																   "Dirección prueba sucursal 1");
		Direccion direccionSucursal2 = utilsMethods.crearDireccion(3, "Provincia prueba sucursal 2", 
				   												   "Ciudad prueba sucursal 2", 
				   											       "Dirección prueba sucursal 2");
		direccionesSucursales.add(direccionSucursal1);
		direccionesSucursales.add(direccionSucursal2);
		clienteDireccionesDTO.setDireccionesSucursales(direccionesSucursales);
		clienteDireccionesDTO.setDireccionMatriz(direccionMatriz);
		clienteDireccionesDTO.toString();
		// Aserciones y verificaciones
		assertEquals(direccionesSucursales, clienteDireccionesDTO.getDireccionesSucursales());
		assertEquals(direccionMatriz, clienteDireccionesDTO.getDireccionMatriz());

	}

}
