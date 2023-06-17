package ec.unexus.prueba.servicio.tony.veas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.repositories.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		super();
		this.clienteRepository = clienteRepository;
	}
	
	public List<Cliente> getClientes() {
		return clienteRepository.findAll();
	}
	
	public List<ClienteDTO> buscarClientes(String search) {
        List<Cliente> clientes = clienteRepository.findByNumeroIdentificacionContainingOrNombresContaining(search, search);
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
	
	public ClienteDTO convertToDTO(Cliente cliente) {
	    ClienteDTO clienteDTO = new ClienteDTO();
	    clienteDTO.setId(cliente.getId());
	    clienteDTO.setIdentificationType(cliente.getTipoIdentificacion());
	    clienteDTO.setIdentificationNumber(cliente.getNumeroIdentificacion());
	    clienteDTO.setNames(cliente.getNombres());
	    clienteDTO.setEmail(cliente.getCorreo());
	    clienteDTO.setCellphone(cliente.getNumeroCelular());
	    // Usamos la dirección de la matriz para llenar estos campos
	    Direccion direccionMatriz = cliente.getDireccionMatriz();
	    clienteDTO.setMainProvince(direccionMatriz.getProvincia());
	    clienteDTO.setMainCity(direccionMatriz.getCiudad());
	    clienteDTO.setMainAddress(direccionMatriz.getDireccion());

	    return clienteDTO;
	}
	
	public Cliente findByNumeroIdentificacion(String numeroIdentificacion) {
	    return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
	    return clienteRepository.save(cliente);
	}
	
	public ClienteDTO update(ClienteDTO clienteDTO) {
	    Cliente clienteExistente = clienteRepository.findById(clienteDTO.getId()).orElseThrow(() -> 
	        new ResponseStatusException(
	                HttpStatus.NOT_FOUND, "No se encontró un cliente con el ID proporcionado"
	        )
	    );
	    
	    clienteExistente.setTipoIdentificacion(clienteDTO.getIdentificationType());
	    clienteExistente.setNumeroIdentificacion(clienteDTO.getIdentificationNumber());
	    clienteExistente.setNombres(clienteDTO.getNames());
	    clienteExistente.setCorreo(clienteDTO.getEmail());
	    clienteExistente.setNumeroCelular(clienteDTO.getCellphone());
	    
	    Cliente clienteActualizado = clienteRepository.save(clienteExistente);
	    
	    return convertToDTO(clienteActualizado);
	}

	public Cliente findById(Integer id) {
	    return clienteRepository.findById(id).orElseThrow(() -> 
	        new RuntimeException("Cliente no encontrado con id: " + id));
	}

	
	@Transactional
	public void eliminarCliente(Integer id) {
	    Cliente clienteParaEliminar = clienteRepository.findById(id).orElseThrow(() -> 
	        new ResponseStatusException(
	                HttpStatus.NOT_FOUND, "No se encontró un cliente con el ID proporcionado"
	        )
	    );
	    clienteRepository.deleteById(id);
	}
	
	
	
}
