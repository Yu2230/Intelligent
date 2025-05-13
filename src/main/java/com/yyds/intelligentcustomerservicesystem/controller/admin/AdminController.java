package com.yyds.intelligentcustomerservicesystem.controller.admin;

import com.yyds.intelligentcustomerservicesystem.data.login.LoginDTO;
import com.yyds.intelligentcustomerservicesystem.data.login.LoginVO;
import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryDTO;
import com.yyds.intelligentcustomerservicesystem.data.salary.SendSalaryVO;
import com.yyds.intelligentcustomerservicesystem.result.Result;
import com.yyds.intelligentcustomerservicesystem.service.SalaryService;
import com.yyds.intelligentcustomerservicesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private SalaryService salaryService;
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO adminDTO){
        LoginVO loginVO = userService.login(adminDTO);
        return Result.OK(loginVO);
    }

    /**
     * 发工资
     * @param sendSalaryDTO
     * @return
     */
    @PostMapping("/paying")
    public Result<SendSalaryVO> sendSalary(@RequestBody SendSalaryDTO sendSalaryDTO){
        SendSalaryVO sendSalaryVO = salaryService.sendSalary(sendSalaryDTO);
        return Result.OK(sendSalaryVO);
    }

}
