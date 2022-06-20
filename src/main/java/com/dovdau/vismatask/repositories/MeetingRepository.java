package com.dovdau.vismatask.repositories;

import com.dovdau.vismatask.models.Meeting;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Repository
@NoArgsConstructor
public class MeetingRepository {

    private static final String MEETINGS_JSON_PATH = "src/main/resources/json/meetings.json";
    private final ObjectMapper objectMapper = createObjectMapper();

    public Meeting writeMeetingData(List<Meeting> meetings){
        try {
            objectMapper.writeValue(Paths.get(MEETINGS_JSON_PATH).toFile(), meetings);
        } catch (IOException e){
            System.out.println("File not found");
        }
        return null;
    }

    public List<Meeting> readMeetingData(){
        try {
            return objectMapper.readValue(Paths.get(MEETINGS_JSON_PATH).toFile(), new TypeReference<>() {});
        } catch (IOException e){
            System.out.println("File not found");
        }
        return null;
    }

    private ObjectMapper createObjectMapper() {

        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
