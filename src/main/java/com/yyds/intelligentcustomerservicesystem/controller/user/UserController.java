package com.yyds.intelligentcustomerservicesystem.controller.user;



import com.yyds.intelligentcustomerservicesystem.data.login.LoginDTO;
import com.yyds.intelligentcustomerservicesystem.data.login.LoginVO;
import com.yyds.intelligentcustomerservicesystem.result.Result;
import com.yyds.intelligentcustomerservicesystem.service.UserService;
import com.yyds.intelligentcustomerservicesystem.data.register.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO registerDTO)throws Exception {
        userService.register(registerDTO);
    }

    @PostMapping("/sendCode")
    public void sendCode(String email){
        userService.sendCode(email);
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        LoginVO loginVO = userService.login(loginDTO);
        //登录后生成Jwt令牌
        return Result.OK(loginVO);
    }
}
