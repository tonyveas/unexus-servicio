package ec.unexus.prueba.servicio.tony.veas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;

/**
 * Esta interfaz es un repositorio que proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Cliente. Hereda de JpaRepository, que es una interfaz Spring Data JPA que proporciona
 * métodos para operaciones de base de datos estándar.
 *
 * Adicionalmente, define algunos métodos personalizados para buscar clientes basados en ciertos 
 * criterios, como buscar por número de identificación y nombres, buscar un solo cliente por número de 
 * identificación, y encontrar todos los clientes con un número de identificación específico.
 **/
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNumeroIdentificacionContainingOrNombresContaining(String numeroIdentificacion, String nombres);

	Cliente findByNumeroIdentificacion(String numeroIdentificacion);

	List<Cliente> findAllByNumeroIdentificacion(String numeroIdentificacion);

}
