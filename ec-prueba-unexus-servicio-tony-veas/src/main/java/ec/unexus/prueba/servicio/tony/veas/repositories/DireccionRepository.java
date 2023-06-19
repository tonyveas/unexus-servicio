package ec.unexus.prueba.servicio.tony.veas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;

/**
 * Esta interfaz es un repositorio que proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Direccion. Hereda de JpaRepository, que es una interfaz Spring Data JPA que proporciona
 * métodos para operaciones de base de datos estándar.
 **/
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

}
