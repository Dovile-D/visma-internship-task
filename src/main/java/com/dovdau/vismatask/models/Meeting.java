package com.dovdau.vismatask.models;

import com.dovdau.vismatask.enums.Category;
import com.dovdau.vismatask.enums.Type;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meeting {
    private int id;
    private String name;
    private Person responsiblePerson;
    private String description;
    private Category category;
    private Type type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private ArrayList<Person> participants;

}
