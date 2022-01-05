package com.musinsa.category.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.category.enums.ErrorMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "message", "suppressed"})
public class BaseException extends RuntimeException {
    protected String className;
    protected String errorMessage;
    protected Integer code;
    protected String ErrorTrace;
    protected HttpStatus httpStatus;
    protected Boolean isCritical;

    public BaseException(ErrorMessage errorMessage, Boolean isCritical) { // 에러메시지만 온 경우
        this.className = this.getClass().getSimpleName();
        this.errorMessage = errorMessage.getErrorMessage();
        this.code = errorMessage.getCode();
        this.httpStatus = errorMessage.getHttpStatus();
        this.isCritical = isCritical;
    }

    public BaseException(String className, ErrorMessage errorMessage, Boolean isCritical) { // 에러 메시지 + 클래스네임
        this.className = className;
        this.errorMessage = errorMessage.getErrorMessage();
        this.code = errorMessage.getCode();
        this.httpStatus = errorMessage.getHttpStatus();
        this.isCritical = isCritical;
    }

}
