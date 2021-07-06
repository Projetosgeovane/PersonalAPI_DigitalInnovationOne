package com.digitalinnovation.personalapi.service;


import com.digitalinnovation.personalapi.dto.request.PersonDTO;
import com.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personalapi.entity.Person;
import com.digitalinnovation.personalapi.mapper.PersonMapper;
import com.digitalinnovation.personalapi.repository.PersonRepository;
import com.digitalinnovation.personalapi.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.Instance;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = PersonMapper.Instance.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "created person with Id ");

    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }


    public void delete(Long id) throws  PersonNotFoundException{
       verifyIfExists(id);
       personRepository.deleteById(id);
    }


    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with Id ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String created_person_with_id_) {
        return MessageResponseDTO
                .builder()
                .message("Update person with Id " + id)
                .build();
    }
}
