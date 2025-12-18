package io.github.aplaraujo.services.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private int numOfErrors;
    private String message;
}
