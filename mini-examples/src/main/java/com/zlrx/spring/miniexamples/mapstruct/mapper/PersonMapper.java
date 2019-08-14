package com.zlrx.spring.miniexamples.mapstruct.mapper;

import com.zlrx.spring.miniexamples.mapstruct.model.Human;
import com.zlrx.spring.miniexamples.mapstruct.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
            @Mapping(target = "idNumber", source = "identification")
    })
    Person mapHumanToPerson(Human human);

}
