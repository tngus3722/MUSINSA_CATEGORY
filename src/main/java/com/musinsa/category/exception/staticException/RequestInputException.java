package com.musinsa.category.exception.staticException;

import com.musinsa.category.enums.ErrorMessage;
import com.musinsa.category.exception.BaseException;

public class RequestInputException extends BaseException {

    public RequestInputException(String className, ErrorMessage errorMessage, Boolean isCritical) {
        super(className, errorMessage, isCritical);
    }

    public RequestInputException(ErrorMessage errorMessage, Boolean isCritical) {
        super(errorMessage, isCritical);
    }
}