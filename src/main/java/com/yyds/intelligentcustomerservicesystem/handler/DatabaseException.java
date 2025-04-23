package com.yyds.intelligentcustomerservicesystem.handler;


import com.yyds.intelligentcustomerservicesystem.constants.ErrorEnum;
import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException {
    private final int code;

    public DatabaseException(String message){
        super(message);

        this.code = ErrorEnum.SUCCESS.getCode();

    }

    public DatabaseException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());

        this.code = errorEnum.getCode();

    }

    public DatabaseException(ErrorEnum errorEnum, String message){
        super(message);

        this.code = errorEnum.getCode();

    }

}
