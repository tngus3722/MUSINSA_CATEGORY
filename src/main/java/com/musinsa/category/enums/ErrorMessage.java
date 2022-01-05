package com.musinsa.category.enums;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
    UNDEFINED_EXCEPTION(0,"정의되지 않은 에러입니다.",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAIL_EXCEPTION(1,"입력값의 조건이 올바르지 않습니다",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXIST_EXCEPTION(2,"카테고리가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PARENT_CATEGORY_NOT_EXIST_EXCEPTION(3,"부모 카테고리가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PARENT_CATEGORY_VALUE_EXCEPTION(4,"자기 자신은 자기 자신의 부모 카테고리가 될 수 없습니다.", HttpStatus.BAD_REQUEST);

    Integer code;
    String errorMessage;
    HttpStatus httpStatus;

    ErrorMessage(int code, String errorMessage, HttpStatus httpStatus) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }


    public Integer getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
