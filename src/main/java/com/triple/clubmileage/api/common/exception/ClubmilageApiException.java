package com.triple.clubmileage.api.common.exception;

import com.triple.clubmileage.api.enumtype.exception.ClubmileageExceptionCode;

public class ClubmilageApiException extends RuntimeException {

    public ClubmileageExceptionCode exceptionCode;

    public ClubmilageApiException() {
        super();
    }

    public ClubmilageApiException(ClubmileageExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ClubmileageExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
