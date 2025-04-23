package com.yyds.intelligentcustomerservicesystem.data.login;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LoginVO {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private int sex;
    private String avatar;
    private LocalDateTime createTime;
    private String token;
}
