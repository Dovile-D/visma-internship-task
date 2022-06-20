package com.dovdau.vismatask.models;

import com.dovdau.vismatask.enums.Category;
import com.dovdau.vismatask.enums.Type;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MeetingFilter {

    private final String description;
    private final int responsiblePersonId;
    private final Category category;
    private final Type type;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final int participants;

}
