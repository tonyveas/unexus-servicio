package ec.unexus.prueba.servicio.tony.veas.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
	private final DataSourceProperties dataSourceProperties;

	public DatabaseConfiguration(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	/* Método para obtener el DataSource */
	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder
			   .create()
			   .driverClassName(dataSourceProperties.getDriverClassName()) // Establece la clase controlador de la base de datos
			   .url(dataSourceProperties.getUrl()) // Establece la URL de conexión a la base de datos
			   .username(dataSourceProperties.getUsername()) // Establece el nombre de usuario para la conexión a la base de datos
			   .password(dataSourceProperties.getPassword()) // Establecer la contraseña para la conexión a la base de datos
			   .build(); // Construir y retornar el DataSource
	}
}
