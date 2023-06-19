package ec.unexus.prueba.servicio.tony.veas.dto;

import jakarta.validation.constraints.NotEmpty;

public class DireccionDTO {

	private Integer id;
	
	@NotEmpty(message = "El campo de provincia o mainProvince no debe estar vacío.")
	private String mainProvince;
	
	@NotEmpty(message = "El campo de ciudad o mainCity no debe estar vacío.")
	private String mainCity;
	
	@NotEmpty(message = "El campo de direccion o mainAddress no debe estar vacío.")
	private String mainAddress;

	private String typeAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainProvince() {
		return mainProvince;
	}

	public void setMainProvince(String mainProvince) {
		this.mainProvince = mainProvince;
	}

	public String getMainCity() {
		return mainCity;
	}

	public void setMainCity(String mainCity) {
		this.mainCity = mainCity;
	}

	public String getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	public String getTypeAddress() {
		return typeAddress;
	}

	public void setTypeAddress(String typeAddress) {
		this.typeAddress = typeAddress;
	}

	@Override
	public String toString() {
		return "DireccionDTO [id=" + id + ", mainProvince=" + mainProvince + ", mainCity=" + mainCity + ", mainAddress="
				+ mainAddress + "]";
	}

}
