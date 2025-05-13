package com.yyds.intelligentcustomerservicesystem.data.salary;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class SendSalaryDTO {
    private List<Long> userId;
}
