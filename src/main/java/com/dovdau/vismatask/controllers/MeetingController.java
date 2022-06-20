package com.dovdau.vismatask.controllers;

import com.dovdau.vismatask.enums.Category;
import com.dovdau.vismatask.enums.Type;
import com.dovdau.vismatask.models.Meeting;
import com.dovdau.vismatask.models.MeetingFilter;
import com.dovdau.vismatask.models.Person;
import com.dovdau.vismatask.services.MeetingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api")
public class MeetingController {

    @Autowired
    private MeetingServiceImpl meetingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/meetings")
    public Meeting createNewMeeting(@RequestBody Meeting meeting) {
        meetingService.addMeeting(meeting);
        return meeting;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/meetings/{meetingId}")
    public void removeMeeting(@PathVariable int userId, @PathVariable int meetingId) {
        meetingService.removeMeeting(meetingId, userId);
    }

    @PostMapping("/meetings/{meetingId}")
    public String addParticipant(@PathVariable int meetingId, @RequestBody Person newParticipant) {
        return meetingService.addParticipant(meetingId, newParticipant);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/meetings/{meetingId}/{userId}")
    public void removeParticipant(@PathVariable int meetingId, @PathVariable int userId) {
        meetingService.removeParticipant(meetingId, userId);
    }

    @GetMapping("/meetings")
    public List<Meeting> findMeetings(
            @RequestParam(required = false) Integer responsibleId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Integer participants
    ) {
        MeetingFilter filterParams = MeetingFilter.builder()
                .responsiblePersonId(responsibleId)
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .participants(participants)
                .category(category)
                .type(type)
                .build();

        return meetingService.findMeetings(filterParams);
    }
}
