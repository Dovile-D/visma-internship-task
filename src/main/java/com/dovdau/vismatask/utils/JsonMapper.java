package com.dovdau.vismatask.utils;

import com.dovdau.vismatask.enums.Category;
import com.dovdau.vismatask.enums.Type;
import com.dovdau.vismatask.models.Meeting;
import com.dovdau.vismatask.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {

    public static List<Meeting> fromJsonArray(JSONArray jsonArray) {
        List<Meeting> meetingList = new ArrayList<Meeting>();

        //Converting jsonData string into JSON object and adding to array
        for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
            ArrayList<Person> participants = new ArrayList<>();
            for (JSONObject object : (Iterable<JSONObject>) (JSONArray) jsonObject.get("participants")) {
                participants.add(new Person(
                       ((Number) object.get("id")).intValue(),
                       (String) object.get("name"),
                       (String) object.get("position"))
                );
            }

            Meeting meeting = new Meeting(
                    ((Number) jsonObject.get("id")).intValue(),
                    (String) jsonObject.get("name"),
                    (Person) jsonObject.get("responsiblePerson"),
                    (String) jsonObject.get("description"),
                    Category.valueOf((String) jsonObject.get("category")),
                    Type.valueOf((String) jsonObject.get("type")),
                    LocalDateTime.parse((String) jsonObject.get("startDate")),
                    LocalDateTime.parse((String) jsonObject.get("endDate")),
                    participants);
            meetingList.add(meeting);
        }
        return meetingList;
    }

    public static String toJsonString(List<Meeting> meetingList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String json = "";
        json = mapper.writeValueAsString(meetingList);
        return json;
    }
}
