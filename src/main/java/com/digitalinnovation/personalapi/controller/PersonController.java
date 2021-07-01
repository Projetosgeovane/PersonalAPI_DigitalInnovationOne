package com.digitalinnovation.personalapi.controller;


import com.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personalapi.entity.Person;
import com.digitalinnovation.personalapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {


    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
    return personService.createPerson(person);

    }

}
