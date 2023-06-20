package ec.unexus.prueba.servicio.tony.veas.utils;

import java.util.List;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;

/*
 * Es una clase útil en el contexto de pruebas unitarias. Se puede usar estos métodos 
 * para crear objetos de prueba (ClienteDTO y Cliente) con valores específicos 
 * sin tener que repetir el código en cada caso de prueba. De esta forma, puedes tener un código más limpio y mantenible.
 * */
public class UtilsMethods {
	
	public ClienteDTO createClienteDTO(Integer id, String names, String identificationType, String identificationNumber, String email, String cellPhone, String mainProvince, String mainCity,
			String mainAddress, String typeAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setId(id);
		dto.setIdentificationType(identificationType);
		dto.setIdentificationNumber(identificationNumber);
		dto.setEmail(email);
		dto.setCellphone(cellPhone);
		dto.setProvince(mainProvince);
		dto.setCity(mainCity);
		dto.setAddress(mainAddress);
		dto.setTypeAddress(typeAddress);
		return dto;
	}
	
	public ClienteDTO createClienteDTO(String names, String identificationNumber, String mainProvince, String mainCity,
			String mainAddress, String typeAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setIdentificationNumber(identificationNumber);
		dto.setProvince(mainProvince);
		dto.setCity(mainCity);
		dto.setAddress(mainAddress);
		dto.setTypeAddress(typeAddress);
		return dto;
	}

	public ClienteDTO createClienteDTOWithID(String names, String identificationNumber) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setIdentificationNumber(identificationNumber);
		return dto;
	}

	public ClienteDTO createClienteDTO(String names, String mainProvince, String mainCity, String mainAddress,
			String typeAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setProvince(mainProvince);
		dto.setCity(mainCity);
		dto.setAddress(mainAddress);
		dto.setTypeAddress(typeAddress);
		return dto;
	}

	public ClienteDTO createClienteDTO(String names, String mainProvince, String mainCity, String mainAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setProvince(mainProvince);
		dto.setCity(mainCity);
		dto.setAddress(mainAddress);
		return dto;
	}
	
	public Cliente createClienteEntity(String names, List<Direccion> direccionesSucursales) {
		Cliente entity = new Cliente();
		entity.setNombres(names);
		entity.setDireccionesSucursales(direccionesSucursales);
		return entity;
	}

	public Cliente createClienteEntity(String names) {
		Cliente entity = new Cliente();
		entity.setNombres(names);
		return entity;
	}

	public Cliente createClienteEntityWithId(String identificationNumber) {
		Cliente entity = new Cliente();
		entity.setNumeroIdentificacion(identificationNumber);
		return entity;
	}

	public Direccion crearDireccion(Integer id, String provincia, String ciudad, String direccion) {
		Direccion direccion1 = new Direccion();
		direccion1.setId(id);
		direccion1.setProvincia(provincia);
		direccion1.setCiudad(ciudad);
		direccion1.setDireccion(direccion);
		direccion1.setTipoDireccion(TipoDireccion.MATRIZ);
		return direccion1;
	}
	
	public DireccionDTO crearDireccionDTO(Integer id, String provincia, String ciudad, String direccion, String tipoDireccion) {
		DireccionDTO direccion1 = new DireccionDTO();
		direccion1.setId(id);
		direccion1.setProvince(provincia);
		direccion1.setCity(ciudad);
		direccion1.setAddress(direccion);
		direccion1.setTypeAddress(tipoDireccion);
		return direccion1;
	}
	
}
