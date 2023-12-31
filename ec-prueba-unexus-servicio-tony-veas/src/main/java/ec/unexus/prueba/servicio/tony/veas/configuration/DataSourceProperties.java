package ec.unexus.prueba.servicio.tony.veas.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que representa las propiedades de configuración para la base de datos.
 * Se utiliza para almacenar las propiedades de URL, nombre de usuario, contraseña y 
 * clase del controlador de la base de datos.
 */
@Configuration
@ConfigurationProperties(prefix = "app.datasource")
public class DataSourceProperties {

	private String url;
	private String username;
	private String password;
	private String driverClassName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	@Override
	public String toString() {
		return "DataSourceProperties [url=" + url + ", username=" + username + ", driverClassname=" + driverClassName
				+ "]";
	}

}
