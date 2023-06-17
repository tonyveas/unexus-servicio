package ec.unexus.prueba.servicio.tony.veas.dto;

public class DireccionDTO {

	private Integer id;
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
