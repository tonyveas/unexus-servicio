package ec.unexus.prueba.servicio.tony.veas.dto;

import java.util.List;

import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;

public class ClienteDireccionesDTO {
	private Direccion direccionMatriz;
	private List<Direccion> direccionesSucursales;

	public Direccion getDireccionMatriz() {
		return direccionMatriz;
	}

	public void setDireccionMatriz(Direccion direccionMatriz) {
		this.direccionMatriz = direccionMatriz;
	}

	public List<Direccion> getDireccionesSucursales() {
		return direccionesSucursales;
	}

	public void setDireccionesSucursales(List<Direccion> direccionesSucursales) {
		this.direccionesSucursales = direccionesSucursales;
	}

	@Override
	public String toString() {
		return "ClienteDireccionDTO [direccionMatriz=" + direccionMatriz + ", direccionesSucursales="
				+ direccionesSucursales + "]";
	}

}
