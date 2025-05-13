package com.yyds.intelligentcustomerservicesystem.service;

import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryDTO;
import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryVO;

public interface SalaryService {
    SendSalaryVO sendSalary(SendSalaryDTO sendSalaryDTO);
}
