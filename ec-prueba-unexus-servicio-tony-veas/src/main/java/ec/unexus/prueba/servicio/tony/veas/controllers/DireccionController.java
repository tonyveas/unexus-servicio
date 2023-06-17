package ec.unexus.prueba.servicio.tony.veas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ec.unexus.prueba.servicio.tony.veas.TipoDireccion;
import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Cliente;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.services.ClienteService;
import ec.unexus.prueba.servicio.tony.veas.services.DireccionService;

@RestController
public class DireccionController {

	private final DireccionService direccionService;
    private final ClienteService clienteService;

    public DireccionController(DireccionService direccionService, ClienteService clienteService) {
        this.direccionService = direccionService;
        this.clienteService = clienteService;
    }

    @PostMapping("/direcciones/{idCliente}")
    public ResponseEntity<Direccion> agregarDireccion(@PathVariable Integer idCliente, @RequestBody DireccionDTO direccionDTO) {
        Cliente cliente = clienteService.findById(idCliente);

        // Check if there is already a matriz address for this cliente
        if ( ( direccionDTO.getTypeAddress() == null || TipoDireccion.MATRIZ.name().equalsIgnoreCase(direccionDTO.getTypeAddress())) && cliente.getDireccionMatriz() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El cliente ya tiene una dirección matriz."
            );
        }

        Direccion nuevaDireccion = direccionService.createFromDTO(direccionDTO);
        nuevaDireccion.setCliente(cliente);
        Direccion direccionGuardada = direccionService.save(nuevaDireccion);

        // If this is a matriz address, we need to update the Cliente to set it as such
        if ( direccionDTO.getTypeAddress() == null || TipoDireccion.MATRIZ.name().equalsIgnoreCase(direccionDTO.getTypeAddress())) {
            cliente.setDireccionMatriz(direccionGuardada);
            clienteService.save(cliente);
        }

        return ResponseEntity.ok(direccionGuardada);
    }

    @GetMapping("/direcciones/clientes/{idCliente}")
    public ResponseEntity<ClienteDireccionesDTO> getDireccionesCliente(@PathVariable("idCliente") Integer idCliente) {
        ClienteDireccionesDTO direcciones = direccionService.getDireccionesCliente(idCliente);
        return ResponseEntity.ok(direcciones);
    }
    
}
