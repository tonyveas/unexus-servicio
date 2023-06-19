package ec.unexus.prueba.servicio.tony.veas.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataSourcePropertiesTest {

	@Test
	public void testDataSourceProperties() {
		DataSourceProperties dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setUrl("jdbc:postgresql://localhost:5432/unexus-database");
		dataSourceProperties.setUsername("postgres");
		dataSourceProperties.setPassword("root");
		dataSourceProperties.setDriverClassName("org.postgresql.Driver");
		dataSourceProperties.toString();
		assertEquals("jdbc:postgresql://localhost:5432/unexus-database", dataSourceProperties.getUrl());
		assertEquals("postgres", dataSourceProperties.getUsername());
		assertEquals("root", dataSourceProperties.getPassword());
		assertEquals("org.postgresql.Driver", dataSourceProperties.getDriverClassName());

	}

}
