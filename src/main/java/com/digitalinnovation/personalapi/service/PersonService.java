package com.digitalinnovation.personalapi.service;


import com.digitalinnovation.personalapi.dto.request.PersonDTO;
import com.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personalapi.entity.Person;
import com.digitalinnovation.personalapi.mapper.PersonMapper;
import com.digitalinnovation.personalapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.Instance;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = PersonMapper.Instance.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();

    }
}
