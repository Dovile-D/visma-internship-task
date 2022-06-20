package com.dovdau.vismatask.services;

import com.dovdau.vismatask.models.Meeting;
import com.dovdau.vismatask.models.Person;
import com.dovdau.vismatask.utils.JsonMapper;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MeetingServiceImpl implements MeetingService {
    private static final String MEETINGS_JSON_PATH = "src/main/resources/json/meetings.json";

    private static ArrayList<Meeting> meetingList;
    @Value(MEETINGS_JSON_PATH)
    private String jsonData;


    // default repository
    public MeetingServiceImpl() {
        this(null);
    }

    public MeetingServiceImpl(String json) {
        
        meetingList = new ArrayList<>();
        if (json != null) {
            jsonData = json;
        }
    }

    // write json
    public void saveData() {
        
        try (FileWriter file = new FileWriter(jsonData)) {
            String json = JsonMapper.toJsonString(meetingList);
            file.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load Json data from a file:
    public void loadData() {
        
        File jsonfile = new File(MEETINGS_JSON_PATH);
        if (!jsonfile.exists() || jsonfile.isDirectory() || jsonfile.length() == 0) {
            return;
        }
        try (FileReader file = new FileReader(jsonData)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(file);
            meetingList = (ArrayList<Meeting>) JsonMapper.fromJsonArray(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // find meeting by id
    public Meeting findMeeting(int id) {
        
        loadData();
        Optional<Meeting> meetingOptional = meetingList.stream().filter(meet -> meet.getId() == id).findFirst();
        if (!meetingOptional.isEmpty()) {
            return meetingOptional.get();
        }
        return null;
    }

    // find all meetings
    public List<Meeting> findMeetings() {
        
        loadData();
        return meetingList;
    }


    public void addMeeting(Meeting newMeeting) {
        
        meetingList.add(newMeeting);
        saveData();
    }

    public void removeMeeting(int id, int responsiblePersonId) {
        
        Meeting meeting = findMeeting(id);
        if (meeting != null && meeting.getResponsiblePerson().getId() != responsiblePersonId) {
            meetingList.remove(meeting);
            saveData();
        }
    }

    public String addParticipant(int meetingId, Person participant) {
        
        Meeting meeting = findMeeting(meetingId);
        if (meeting == null) {
            return "Meeting with id: " + meetingId + " not found";
        }
        int index = meetingList.indexOf(meeting);
        Stream<Person> attendeeStream = meeting.getParticipants().stream();
        // Check if person is already in this meeting
        if (attendeeStream.anyMatch(a -> a.getId() != participant.getId())) {
            List<Person> meetingParticipants = meeting.getParticipants();
            meetingParticipants.add(participant);
            meeting.setParticipants((ArrayList<Person>) meetingParticipants);
            meetingList.set(index, meeting);
            saveData();
            return meetingParticipants.toString();
        }
        else {
            return "This person is already in meeting";
        }
    }

    public void removeParticipant(int meetingId, int participantId) {
        
        Meeting meeting = findMeeting(meetingId);
        int index = meetingList.indexOf(meeting);
        if (meeting != null) {
            // check if participant is not the owner of a meeting
            if (meeting.getResponsiblePerson().getId() != participantId) {
                Stream<Person> participantStream = meeting.getParticipants().stream();
                ArrayList<Person> meetingParticipants = participantStream.filter(att -> att.getId() != participantId)
                        .collect(Collectors.toCollection(ArrayList<Person>::new));
                meeting.setParticipants(meetingParticipants);
                meetingList.set(index, meeting);
                saveData();
            }
        }
    }
}
