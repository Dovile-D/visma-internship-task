package com.dovdau.vismatask.services;

import com.dovdau.vismatask.models.Meeting;
import com.dovdau.vismatask.models.MeetingFilter;
import com.dovdau.vismatask.models.Person;

import java.util.List;

public interface MeetingService {
    void saveData();

    void loadData();

    Meeting findMeeting(int id);

    List<Meeting> findMeetings();

    void addMeeting(Meeting newMeeting);

    void removeMeeting(int id, int responsiblePersonId);

    String addParticipant(int meetingId, Person participant);

    void removeParticipant(int meetingId, int participantId);
}
