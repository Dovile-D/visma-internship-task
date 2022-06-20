package com.dovdau.vismatask.models;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private int id;
    private String name;
    private String position;
}
