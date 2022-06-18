package com.triple.clubmileage.api.common.exception.handler;

import com.triple.clubmileage.api.common.exception.ClubmilageApiException;
import com.triple.clubmileage.api.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionRestControllerAdvice {

    @ExceptionHandler(value = {ClubmilageApiException.class})
    public ResponseEntity<ApiErrorResponse> clubmileageApiExceptionHandler(ClubmilageApiException e) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setResult(-1);
        apiErrorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
