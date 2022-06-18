package com.triple.clubmileage.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {

    private int result;
    private String message;
}
