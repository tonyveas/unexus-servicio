package ec.unexus.prueba.servicio.tony.veas.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	private String tipoIdentificacion;

	@NotEmpty
	@Size(min = 10, 
		  max = 10, 
		  message = "La cantidad de dígitos del número de identificación "
					+ "(numeroIdentificacion/identificationNumber) deber ser 10.")
	private String numeroIdentificacion;

	@NotEmpty(message = "El campo de nombres o names no debe estar vacío.")
	private String nombres;

	@NotEmpty(message = "El campo de correo o email no debe estar vacío.")
	@Email(message = "Debe ingresar una dirección de correo electrónico con formato correcto")
	private String correo;

	@NotEmpty(message = "El campo de numeroCelular o cellphone no debe estar vacío.")
	@Size(min = 10,
	      max = 10, 
	      message = "La cantidad de dígitos del numeroCelular o cellphone "
			+ "deber ser 10.")
	private String numeroCelular;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "direccion_matriz_id", referencedColumnName = "id")
	private Direccion direccionMatriz;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Direccion> direccionesSucursales = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public List<Direccion> getDireccionesSucursales() {
		return direccionesSucursales;
	}

	public void setDireccionesSucursales(List<Direccion> direccionesSucursales) {
		this.direccionesSucursales = direccionesSucursales;
	}

	public Direccion getDireccionMatriz() {
		return direccionMatriz;
	}

	public void setDireccionMatriz(Direccion direccionMatriz) {
		this.direccionMatriz = direccionMatriz;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipoIdentificacion=" + tipoIdentificacion + ", numeroIdentificacion="
				+ numeroIdentificacion + ", nombres=" + nombres + ", correo=" + correo + ", numeroCelular="
				+ numeroCelular + ", direccionesSucursales=" + direccionesSucursales + ", direccionMatriz="
				+ direccionMatriz + "]";
	}

}
