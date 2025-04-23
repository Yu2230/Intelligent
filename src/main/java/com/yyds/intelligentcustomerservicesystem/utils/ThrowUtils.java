package com.yyds.intelligentcustomerservicesystem.utils;

import com.yyds.intelligentcustomerservicesystem.constants.ErrorEnum;
import com.yyds.intelligentcustomerservicesystem.exception.BusinessException;

public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorEnum
     */
    public static void throwIf(boolean condition, ErrorEnum errorEnum) {
        throwIf(condition, new BusinessException(errorEnum));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorEnum
     * @param message
     */
    public static void throwIf(boolean condition, ErrorEnum errorEnum, String message) {
        throwIf(condition, new BusinessException(errorEnum, message));
    }
}
