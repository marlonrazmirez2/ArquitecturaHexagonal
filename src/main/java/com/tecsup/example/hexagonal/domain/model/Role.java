package com.tecsup.example.hexagonal.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    public static final Role USER = Role.builder().id(1L).name("USER").build();
    public static final Role ADMIN = Role.builder().id(2L).name("ADMIN").build();
    public static final Role MONITOR = Role.builder().id(3L).name("MONITOR").build();


    private Long id;
    private String name;
    private String description;


}
