package ec.unexus.prueba.servicio.tony.veas.configuration;

import static org.junit.jupiter.api.Assertions.*;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DatabaseConfigurationTest {

	//Declaración de variables que se usan en el test
    private DataSourceProperties mockProperties;
    private DatabaseConfiguration config;

    // Inicialización de variables que se usan en las pruebas
    @BeforeEach
    public void setup() {
        mockProperties = Mockito.mock(DataSourceProperties.class);
        config = new DatabaseConfiguration(mockProperties);
    }

    @Test
    public void testGetDataSource() {
    	// Implementacion de mocks
        Mockito.when(mockProperties.getDriverClassName()).thenReturn("org.postgresql.Driver");
        Mockito.when(mockProperties.getUrl()).thenReturn("jdbc:postgresql://localhost:5432/unexus-database");
        Mockito.when(mockProperties.getUsername()).thenReturn("postgres");
        Mockito.when(mockProperties.getPassword()).thenReturn("root");

        // Ejecución del método
        DataSource dataSource = config.getDataSource();

        // Assersiones y verificaciones
        assertNotNull(dataSource);
    }

    @Test
    public void testGetDataSource_WithMissingDriver() {
    	// Implementacion de mocks
        Mockito.when(mockProperties.getDriverClassName()).thenReturn("");
        Mockito.when(mockProperties.getUrl()).thenReturn("jdbc:postgresql://localhost:5432/unexus-database");
        Mockito.when(mockProperties.getUsername()).thenReturn("postgres");
        Mockito.when(mockProperties.getPassword()).thenReturn("root");

        // Assersiones y verificaciones
        assertThrows(RuntimeException.class, () -> config.getDataSource());
    }

    @Test
    public void testGetDataSource_WithInvalidCredentials() {
    	// Implementacion de mocks
        Mockito.when(mockProperties.getDriverClassName()).thenReturn("org.postgresql.Driver");
        Mockito.when(mockProperties.getUrl()).thenReturn("jdbc:postgresql://localhost:5432/unexus-database");
        Mockito.when(mockProperties.getUsername()).thenReturn("invalid_user");
        Mockito.when(mockProperties.getPassword()).thenReturn("invalid_password");

        // Ejecución del método
        DataSource dataSource = config.getDataSource();

        // Assersiones y verificaciones
        assertNotNull(dataSource);
    }
}
