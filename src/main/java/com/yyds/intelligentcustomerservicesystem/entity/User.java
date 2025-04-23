package com.yyds.intelligentcustomerservicesystem.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private int sex;
    private String avatar;
    private LocalDateTime createTime;
}
