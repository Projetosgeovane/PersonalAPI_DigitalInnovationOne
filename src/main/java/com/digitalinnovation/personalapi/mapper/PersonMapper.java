package com.digitalinnovation.personalapi.mapper;

import com.digitalinnovation.personalapi.dto.request.PersonDTO;
import com.digitalinnovation.personalapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper Instance = Mappers.getMapper(PersonMapper.class);


    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
