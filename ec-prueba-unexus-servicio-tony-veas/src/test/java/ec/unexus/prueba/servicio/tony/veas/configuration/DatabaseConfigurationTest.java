package ec.unexus.prueba.servicio.tony.veas.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConfigurationTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Declaramos el mock de DataSourceProperties
    @Mock
    private DataSourceProperties mockProperties;

    // Usamos @InjectMocks para que Mockito cree una instancia de DatabaseConfiguration
    // e inyecte el mock de DataSourceProperties en él.
    @InjectMocks
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
    	try {
	    	// Implementacion de mocks
	        Mockito.when(mockProperties.getDriverClassName()).thenReturn("");
	        Mockito.when(mockProperties.getUrl()).thenReturn("jdbc:postgresql://localhost:5432/unexus-database");
	        Mockito.when(mockProperties.getUsername()).thenReturn("postgres");
	        Mockito.when(mockProperties.getPassword()).thenReturn("root");
	
	        // Assersiones y verificaciones
	        assertThrows(RuntimeException.class, () -> config.getDataSource());
	        
    	} catch (Exception e) {
			logger.info(e.getMessage());
		}
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

