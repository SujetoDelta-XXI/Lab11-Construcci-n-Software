package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	// Buscar por nombre exacto
	List<Pet> findByName(String name);

	// Buscar por tipo (ej. 1=cat, 2=dog...)
	List<Pet> findByTypeId(int typeId);

	// Buscar todas las mascotas de un owner específico
	List<Pet> findByOwnerId(int ownerId);

	// Ya viene por defecto con JpaRepository, pero puedes redefinirlo si quieres
	@Override
	List<Pet> findAll();
}
