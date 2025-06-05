package com.tecsup.petclinic.mappers;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    // DTO → Entity
    Owner toOwner(OwnerDTO dto);

    // Entity → DTO
    OwnerDTO toOwnerDTO(Owner owner);

    // List conversions
    List<OwnerDTO> toOwnerDTOList(List<Owner> ownerList);

    List<Owner> toOwnerList(List<OwnerDTO> dtoList);
}
