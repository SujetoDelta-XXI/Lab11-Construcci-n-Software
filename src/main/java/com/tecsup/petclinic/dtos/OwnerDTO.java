package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la entidad Owner
 *
 * @author
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String telephone;
}
