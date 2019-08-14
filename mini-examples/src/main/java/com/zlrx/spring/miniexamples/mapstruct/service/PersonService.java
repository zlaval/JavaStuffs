package com.zlrx.spring.miniexamples.mapstruct.service;


import com.zlrx.spring.miniexamples.mapstruct.mapper.PersonMapper;
import com.zlrx.spring.miniexamples.mapstruct.model.Human;
import com.zlrx.spring.miniexamples.mapstruct.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private List<Human> humans = new ArrayList<>();

    @Autowired
    private PersonMapper personMapper;

    @PostConstruct
    private void init() {
        humans.add(new Human("Joe", 30, "dspfigjo"));
        humans.add(new Human("Jane", 40, "iouasdf"));
    }

    public List<Person> getPeople() {
        return humans.stream().map(
                human -> personMapper.mapHumanToPerson(human)
        ).collect(Collectors.toList());
    }


}
