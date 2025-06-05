package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mappers.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de dueños (Owner)
 */
@RestController
@Slf4j
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    /**
     * Obtener todos los dueños
     */
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        List<OwnerDTO> ownerDTOs = mapper.toOwnerDTOList(owners);
        return ResponseEntity.ok(ownerDTOs);
    }

    /**
     * Crear un nuevo dueño
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO ownerDTO) {
        Owner newOwner = mapper.toOwner(ownerDTO);
        OwnerDTO createdOwner = mapper.toOwnerDTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
    }

    /**
     * Buscar dueño por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            return ResponseEntity.ok(mapper.toOwnerDTO(owner));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualizar un dueño
     */
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> update(@RequestBody OwnerDTO ownerDTO, @PathVariable Integer id) {
        try {
            Owner existingOwner = ownerService.findById(id);
            existingOwner.setFirstName(ownerDTO.getFirstName());
            existingOwner.setLastName(ownerDTO.getLastName());
            existingOwner.setAddress(ownerDTO.getAddress());
            existingOwner.setCity(ownerDTO.getCity());
            existingOwner.setTelephone(ownerDTO.getTelephone());

            Owner updatedOwner = ownerService.update(existingOwner);
            return ResponseEntity.ok(mapper.toOwnerDTO(updatedOwner));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar un dueño
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted Owner with ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}