package com.yyds.intelligentcustomerservicesystem.constants;

import lombok.Data;

import java.math.BigDecimal;


public class PostConstants {
    public static final String DEFAULT_POST = "职员";
    public static final String MIDDLE_POST = "负责人";
    public static final String HIGH_POST = "经理";
    public static final BigDecimal DEFAULT_PRICE = new BigDecimal("5000.00");
    public static final BigDecimal MIDDLE_PRICE = new BigDecimal("7000.00");
    public static final BigDecimal HIGH_PRICE = new BigDecimal("10000.00");
    public static final int user = 0;
    public static final int admin = 1;
}
