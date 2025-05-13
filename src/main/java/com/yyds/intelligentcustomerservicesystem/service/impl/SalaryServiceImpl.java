package com.yyds.intelligentcustomerservicesystem.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.yyds.intelligentcustomerservicesystem.constants.ErrorEnum;
import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryDTO;
import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryVO;
import com.yyds.intelligentcustomerservicesystem.entity.Salary;
import com.yyds.intelligentcustomerservicesystem.entity.User;
import com.yyds.intelligentcustomerservicesystem.exception.BusinessException;
import com.yyds.intelligentcustomerservicesystem.mapper.SalaryMapper;
import com.yyds.intelligentcustomerservicesystem.mapper.UserMapper;
import com.yyds.intelligentcustomerservicesystem.service.SalaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.yyds.intelligentcustomerservicesystem.constants.PostConstants;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class SalaryServiceImpl implements SalaryService {
    private final UserMapper userMapper;
    private final SalaryMapper salaryMapper;

    public SalaryServiceImpl(UserMapper userMapper, SalaryMapper salaryMapper) {
        this.userMapper = userMapper;
        this.salaryMapper = salaryMapper;
    }

    @Override
    public SendSalaryVO sendSalary(SendSalaryDTO sendSalaryDTO) {
        SendSalaryVO sendSalaryVO = new SendSalaryVO();
        List<Long> userIds = sendSalaryDTO.getUserId();
        for(Long userId : userIds) {
            User user = userMapper.selectById(userId);
            Salary salary = sendByPost(user);
            BeanUtils.copyProperties(salary, sendSalaryVO);
            salaryMapper.insertSalary(salary);
        }
        return sendSalaryVO;
    }
    private Salary sendByPost(User user) {
        Salary salary = new Salary();
        switch (user.getPost()){
            case PostConstants.DEFAULT_POST:
                salary.setSalary(PostConstants.DEFAULT_PRICE);
                break;
            case PostConstants.MIDDLE_POST:
                salary.setSalary(PostConstants.MIDDLE_PRICE);
                break;
            case PostConstants.HIGH_POST:
                salary.setSalary(PostConstants.HIGH_PRICE);
                break;
            default:
                throw new BusinessException(ErrorEnum.OPERATION_ERROR);
        }
        salary.setSalaryId(generateId());
        salary.setUserId(user.getUserId());
        //salary.setSendName();
        salary.setCreateTime(LocalDateTime.now());
        return salary;
    }
    private Long generateId() {
        Snowflake snowflake = new Snowflake();
        return snowflake.nextId();
    }
}
