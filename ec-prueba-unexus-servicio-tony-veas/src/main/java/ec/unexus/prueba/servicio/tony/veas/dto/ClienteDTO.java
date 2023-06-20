package ec.unexus.prueba.servicio.tony.veas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) que representa la información de un cliente.
 * Contiene los campos necesarios para identificar y comunicarse con el cliente,
 * como el tipo de identificación, número de identificación, nombres, correo electrónico,
 * número de celular, dirección principal, etc.
 * Usado generalmente, como nuestro "request" al crear o actualizar un cliente.
 */
public class ClienteDTO {

	private Integer id;
	
	/* Tipo de identificación (RUC o CÉDULA) */
	@NotEmpty
    @Pattern(regexp="^(RUC|CÉDULA)$", message="El tipo de identificación debe ser RUC o CÉDULA.")
    private String identificationType;
	
	/* Número de identificación */
	@NotEmpty
	@Size(min = 10, 
		  max = 10, 
		  message = "La cantidad de dígitos del número de identificación "
					+ "(numeroIdentificacion/identificationNumber) deber ser 10.")
	private String identificationNumber;
	
	/* Nombres */
	@NotEmpty(message = "El campo de nombres o names no debe estar vacío.")
	private String names;
	
	/* Correo electrónico */
	@NotEmpty(message = "El campo de correo o email no debe estar vacío.")
	@Email(message = "Debe ingresar una dirección de correo electrónico con formato correcto")
	private String email;
	
	/* Número de teléfono */
	@NotEmpty(message = "El campo de numeroCelular o cellphone no debe estar vacío.")
	@Size(min = 10,
	      max = 10, 
	      message = "La cantidad de dígitos del numeroCelular o cellphone "
			+ "deber ser 10.")
	private String cellphone;
	
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
	 * utiliza para elegir si la dirección asociada al cliente
	 * es una dirección matriz o una dirección sucursal. Si no se indica este 
	 * campo en la solicitud (request), la dirección se asocia automáticamente 
	 * como una dirección matriz.
	 */
	@Pattern(regexp="^(MATRIZ|SUCURSAL)$", message="El tipo de dirección debe ser Matriz o Sucursal.")
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
		return "ClienteDTO [id=" + id + ", identificationType=" + identificationType + ", identificationNumber="
				+ identificationNumber + ", names=" + names + ", email=" + email + ", cellphone=" + cellphone
				+ ", mainProvince=" + province + ", mainCity=" + city + ", mainAddress=" + address
				+ ", typeAddress=" + typeAddress + "]";
	}

}
