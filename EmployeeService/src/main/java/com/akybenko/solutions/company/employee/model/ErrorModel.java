package com.akybenko.solutions.company.employee.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {

    private String timestamp;
    private Integer status;
    private String error;
    private List<Map<String, String>> errors;
    private String message;
    private String path;

    public ErrorModel() {
        timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .format(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC")));
    }
}
