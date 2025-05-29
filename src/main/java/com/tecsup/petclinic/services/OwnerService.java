package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;

/**
 * Service interface for Owner
 * 
 */
public interface OwnerService {

    /**
     * Crear un nuevo dueño
     * 
     * @param owner
     * @return
     */
    Owner create(Owner owner);

    /**
     * Actualizar un dueño existente
     * 
     * @param owner
     * @return
     */
    Owner update(Owner owner);

    /**
     * Eliminar un dueño por ID
     * 
     * @param id
     * @throws OwnerNotFoundException
     */
    void delete(Integer id) throws OwnerNotFoundException;

    /**
     * Buscar dueño por ID
     * 
     * @param id
     * @return
     * @throws OwnerNotFoundException
     */
    Owner findById(Integer id) throws OwnerNotFoundException;

    /**
     * Buscar dueños por apellido
     * 
     * @param lastName
     * @return
     *
     */
    List<Owner> findByLastName(String lastName);

    /**
     * Buscar dueños por ciudad
     * 
     * @param city
     * @return
     */
    List<Owner> findByCity(String city);

    /**
     * Listar todos los dueños
     * 
     * @return
     */
    List<Owner> findAll();
}
