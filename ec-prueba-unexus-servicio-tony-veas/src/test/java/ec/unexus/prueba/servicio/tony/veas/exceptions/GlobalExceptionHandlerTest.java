package ec.unexus.prueba.servicio.tony.veas.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;

public class GlobalExceptionHandlerTest {

	// Creando variable usada en el test
	private GlobalExceptionHandler handler = new GlobalExceptionHandler();

	@Test
	public void handleValidationExceptions() throws NoSuchMethodException {
		// Preparamos el resultado del Binding
		HashMap<String, Object> bindingResultModel = new HashMap<>();
		MapBindingResult bindingResult = new MapBindingResult(bindingResultModel, "target");
		FieldError error = new FieldError("target", "field", "defaultMessage");
		bindingResult.addError(error);
		// Obtenemos un MethodParameter
		Method method = GlobalExceptionHandlerTest.class.getMethod("handleValidationExceptions");
		MethodParameter methodParameter = new MethodParameter(method, -1);
		// Preparamos la excepción
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);
		ResponseEntity<Object> response = handler.handleValidationExceptions(ex);
		// Verificamos el estado HTTP
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// Verificamos que el cuerpo de la respuesta contiene el error
		assertTrue(response.getBody() instanceof Map);
		@SuppressWarnings("unchecked")
		Map<String, String> responseBody = (Map<String, String>) response.getBody();
		assertTrue(responseBody.containsKey("field"));
		assertEquals("defaultMessage", responseBody.get("field"));
	}

	@Test
	public void handleConstraintViolationExceptions() {
		// Preparamos la violación de restricción
		ConstraintViolation<?> violation = mock(ConstraintViolation.class);
		when(violation.getPropertyPath()).thenReturn(mock(Path.class));
		when(violation.getPropertyPath().toString()).thenReturn("field");
		when(violation.getMessage()).thenReturn("defaultMessage");
		// Preparamos la excepción
		ConstraintViolationException ex = new ConstraintViolationException(Set.of(violation));
		ResponseEntity<Object> response = handler.handleConstraintViolationExceptions(ex);
		// Verificamos el estado HTTP
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		// Verificamos que el cuerpo de la respuesta contiene el error
		assertTrue(response.getBody() instanceof Map);
		@SuppressWarnings("unchecked")
		Map<String, String> responseBody = (Map<String, String>) response.getBody();
		assertTrue(responseBody.containsKey("field"));
		assertEquals("defaultMessage", responseBody.get("field"));
	}

}
