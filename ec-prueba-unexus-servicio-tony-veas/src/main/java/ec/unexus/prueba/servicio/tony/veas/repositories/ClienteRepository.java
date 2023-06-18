package ec.unexus.prueba.servicio.tony.veas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNumeroIdentificacionContainingOrNombresContaining(String numeroIdentificacion, String nombres);

	Cliente findByNumeroIdentificacion(String numeroIdentificacion);

	List<Cliente> findAllByNumeroIdentificacion(String numeroIdentificacion);

}
