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

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create().driverClassName(dataSourceProperties.getDriverClassName())
				.url(dataSourceProperties.getUrl()).username(dataSourceProperties.getUsername())
				.password(dataSourceProperties.getPassword()).build();
	}
}
