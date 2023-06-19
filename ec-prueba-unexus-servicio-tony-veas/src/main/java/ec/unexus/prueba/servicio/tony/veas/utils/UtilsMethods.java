package ec.unexus.prueba.servicio.tony.veas.utils;

import java.util.List;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;

public class UtilsMethods {
	public ClienteDTO createClienteDTO(String names, String identificationNumber, String mainProvince, String mainCity,
			String mainAddress, String typeAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setIdentificationNumber(identificationNumber);
		dto.setMainProvince(mainProvince);
		dto.setMainCity(mainCity);
		dto.setMainAddress(mainAddress);
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
		dto.setMainProvince(mainProvince);
		dto.setMainCity(mainCity);
		dto.setMainAddress(mainAddress);
		dto.setTypeAddress(typeAddress);
		return dto;
	}

	public ClienteDTO createClienteDTO(String names, String mainProvince, String mainCity, String mainAddress) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNames(names);
		dto.setMainProvince(mainProvince);
		dto.setMainCity(mainCity);
		dto.setMainAddress(mainAddress);
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

	
}
