package ec.unexus.prueba.servicio.tony.veas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.unexus.prueba.servicio.tony.veas.dto.ClienteDireccionesDTO;
import ec.unexus.prueba.servicio.tony.veas.dto.DireccionDTO;
import ec.unexus.prueba.servicio.tony.veas.entities.Direccion;
import ec.unexus.prueba.servicio.tony.veas.services.DireccionService;

@RestController
public class DireccionController {

	private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping("/direcciones/{idCliente}")
    public ResponseEntity<Direccion> agregarDireccion(@PathVariable Integer idCliente, @RequestBody DireccionDTO direccionDTO) {
        Direccion direccionGuardada = direccionService.agregarDireccion(idCliente, direccionDTO);
        return ResponseEntity.ok(direccionGuardada);
    }

    @GetMapping("/direcciones/clientes/{idCliente}")
    public ResponseEntity<ClienteDireccionesDTO> getDireccionesCliente(@PathVariable("idCliente") Integer idCliente) {
        ClienteDireccionesDTO direcciones = direccionService.getDireccionesCliente(idCliente);
        return ResponseEntity.ok(direcciones);
    }

    
}
