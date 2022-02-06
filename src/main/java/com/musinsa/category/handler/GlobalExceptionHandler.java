package com.musinsa.category.handler;

import com.musinsa.category.enums.ErrorMessage;
import com.musinsa.category.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseException> defaultException(Throwable e) throws IOException {
        BaseException baseException = null;

        if (e instanceof BaseException) {
            ((BaseException) e).setErrorTrace(e.getStackTrace()[0].toString());
            baseException = (BaseException) e;
        }

        // validation exception
        if (e instanceof MethodArgumentNotValidException) {
            baseException = new BaseException(e.getClass().getSimpleName(), ErrorMessage.VALIDATION_FAIL_EXCEPTION, true);
            List<ObjectError> messageList = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            String message = "";
            for (int i = 0; i < messageList.size(); i++) {
                String validationMessage = messageList.get(i).getDefaultMessage();
                message += "[" + validationMessage + "]";
            }
            baseException.setErrorMessage(message);
            baseException.setErrorTrace(e.getStackTrace()[0].toString());
        }

        // unhandled Exception
        if (baseException == null) {
            baseException = new BaseException(e.getClass().getSimpleName(), ErrorMessage.UNDEFINED_EXCEPTION, true);
            baseException.setErrorMessage(e.getMessage());
            baseException.setErrorTrace(e.getStackTrace()[0].toString());
        }

        if (baseException.getIsCritical()) ; // TODO notify some messanger

        return new ResponseEntity<>(baseException, baseException.getHttpStatus());

    }
}
