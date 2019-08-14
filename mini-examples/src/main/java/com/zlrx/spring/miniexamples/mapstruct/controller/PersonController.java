package com.zlrx.spring.miniexamples.mapstruct.controller;

import com.zlrx.spring.miniexamples.mapstruct.model.Person;
import com.zlrx.spring.miniexamples.mapstruct.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

}
