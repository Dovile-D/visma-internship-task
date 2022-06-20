package com.dovdau.vismatask.exceptions;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeetingErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
