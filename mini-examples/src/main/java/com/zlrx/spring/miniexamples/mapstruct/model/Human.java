package com.zlrx.spring.miniexamples.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Human {
    private String name;
    private Integer age;
    private String identification;
}
