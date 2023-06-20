package ec.unexus.prueba.servicio.tony.veas.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * DTO (Data Transfer Object) que representa una dirección.
 * Contiene los campos necesarios para especificar la provincia, ciudad y dirección.
 * Se usa como 'request' para registar una nueva dirección para un cliente
 * 
 */
public class DireccionDTO {

	private Integer id;
	
	/* Provincia */
	@NotEmpty(message = "El campo de provincia o mainProvince no debe estar vacío.")
	private String province;
	
	/* Ciudad */
	@NotEmpty(message = "El campo de ciudad o mainCity no debe estar vacío.")
	private String city;
	
	/* Dirección */
	@NotEmpty(message = "El campo de direccion o mainAddress no debe estar vacío.")
	private String address;

	/**
	 * El campo 'typeAddress' (tipo dirección: MATRIZ o Sucursal) es opcional y se 
	 * utiliza para elegir si la dirección que se va a crear
	 * es una dirección matriz o una dirección sucursal. Si no se indica este 
	 * campo en la solicitud (request), la dirección se asocia automáticamente 
	 * como una dirección sucursal.
	 */
	@Pattern(regexp="^(MATRIZ|SUCURSAL)$", message="El tipo de dirección debe ser Matriz o Sucursal.")
	private String typeAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypeAddress() {
		return typeAddress;
	}

	public void setTypeAddress(String typeAddress) {
		this.typeAddress = typeAddress;
	}

	@Override
	public String toString() {
		return "DireccionDTO [id=" + id + ", mainProvince=" + province + ", mainCity=" + city + ", mainAddress="
				+ address + "]";
	}

}
