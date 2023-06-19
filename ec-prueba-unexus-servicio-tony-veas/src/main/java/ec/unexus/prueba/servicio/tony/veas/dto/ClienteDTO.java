package ec.unexus.prueba.servicio.tony.veas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClienteDTO {

	private Integer id;
	@NotEmpty
	private String identificationType;
	
	@NotEmpty
	@Size(min = 10, 
		  max = 10, 
		  message = "La cantidad de dígitos del número de identificación "
					+ "(numeroIdentificacion/identificationNumber) deber ser 10.")
	private String identificationNumber;
	
	@NotEmpty(message = "El campo de nombres o names no debe estar vacío.")
	private String names;
	
	@NotEmpty(message = "El campo de correo o email no debe estar vacío.")
	@Email(message = "Debe ingresar una dirección de correo electrónico con formato correcto")
	private String email;
	
	@NotEmpty(message = "El campo de numeroCelular o cellphone no debe estar vacío.")
	@Size(min = 10,
	      max = 10, 
	      message = "La cantidad de dígitos del numeroCelular o cellphone "
			+ "deber ser 10.")
	private String cellphone;
	
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

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
		return "ClienteDTO [id=" + id + ", identificationType=" + identificationType + ", identificationNumber="
				+ identificationNumber + ", names=" + names + ", email=" + email + ", cellphone=" + cellphone
				+ ", mainProvince=" + mainProvince + ", mainCity=" + mainCity + ", mainAddress=" + mainAddress
				+ ", typeAddress=" + typeAddress + "]";
	}

}
