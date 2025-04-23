package com.yyds.intelligentcustomerservicesystem.service;

import com.yyds.intelligentcustomerservicesystem.data.login.LoginDTO;
import com.yyds.intelligentcustomerservicesystem.data.login.LoginVO;
import com.yyds.intelligentcustomerservicesystem.data.register.RegisterDTO;
import org.springframework.stereotype.Service;


public interface UserService {
    void sendCode(String email);
    void register(RegisterDTO registerDTO) throws Exception;

    LoginVO login(LoginDTO loginDTO);
}
