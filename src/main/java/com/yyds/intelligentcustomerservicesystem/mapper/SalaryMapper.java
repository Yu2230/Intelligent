package com.yyds.intelligentcustomerservicesystem.mapper;

import com.yyds.intelligentcustomerservicesystem.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper {

    void insertSalary(Salary salary);
}
