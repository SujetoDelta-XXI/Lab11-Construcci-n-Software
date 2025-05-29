package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Owner;

/**
 * Repository for Owner entity
 * 
 * @author 
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // Buscar dueños por apellido
    List<Owner> findByLastName(String lastName);

    // Buscar dueños por ciudad
    List<Owner> findByCity(String city);

    // Buscar todos
    @Override
    List<Owner> findAll();
}
