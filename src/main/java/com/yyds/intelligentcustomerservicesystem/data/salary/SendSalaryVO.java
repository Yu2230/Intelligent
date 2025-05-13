package com.yyds.intelligentcustomerservicesystem.data.salary;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SendSalaryVO {
    private Long salaryId;
    private Long userId;
    private BigDecimal salary;
    private String sendName;
    private LocalDateTime createTime;
}
