package com.swagger.exceptions;

import com.swagger.domain.enums.ExceptionEnum;

public class CommonException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

    public CommonException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.exceptionEnum = exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    @Override
    public String getMessage() {
        return this.exceptionEnum.getMsg();
    }
}
