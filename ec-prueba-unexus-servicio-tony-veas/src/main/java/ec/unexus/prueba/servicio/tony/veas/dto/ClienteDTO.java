package ec.unexus.prueba.servicio.tony.veas.dto;

public class ClienteDTO {

	private Integer id;
	private String identificationType;
	private String identificationNumber;
	private String names;
	private String email;
	private String cellphone;
	private String mainProvince;
	private String mainCity;
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
