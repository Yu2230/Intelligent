package com.yyds.intelligentcustomerservicesystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Snowflake;
import com.yyds.intelligentcustomerservicesystem.constants.PostConstants;
import com.yyds.intelligentcustomerservicesystem.data.login.LoginDTO;
import com.yyds.intelligentcustomerservicesystem.data.login.LoginVO;
import com.yyds.intelligentcustomerservicesystem.service.UserService;
import com.yyds.intelligentcustomerservicesystem.client.MailClient;
import com.yyds.intelligentcustomerservicesystem.constants.ConfigEnum;
import com.yyds.intelligentcustomerservicesystem.constants.ErrorEnum;
import com.yyds.intelligentcustomerservicesystem.constants.TimeOutEnum;
import com.yyds.intelligentcustomerservicesystem.data.register.RegisterDTO;
import com.yyds.intelligentcustomerservicesystem.entity.User;
import com.yyds.intelligentcustomerservicesystem.exception.BusinessException;
import com.yyds.intelligentcustomerservicesystem.mapper.UserMapper;
import com.yyds.intelligentcustomerservicesystem.utils.JwtUtil;
import com.yyds.intelligentcustomerservicesystem.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;

    /**
     * 发送验证码
     *
     * @param email
     */
    @Override
    public void sendCode(String email) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        mailClient.sendMail(email, code);
        redisTemplate.opsForValue().set(email, code, TimeOutEnum.TOKEN_TIME_OUT.getTimeOut(), TimeUnit.MINUTES);
    }

    /**
     * 用户注册
     *
     * @param registerDTO
     */
    @Override
    public void register(RegisterDTO registerDTO) throws BusinessException {
        log.info("用户注册 : {}", registerDTO);
        String email = registerDTO.getEmail();
        String code = registerDTO.getCode();
        //1. 检查email是否已经注册；已经注册返回异常;
        if (isExist(email)) {
            //验证Redis中的code 与 传来的code 是否相等
            String codeRedis = redisTemplate.opsForValue().get(registerDTO.getEmail());
            ThrowUtils.throwIf(!codeRedis.equals(code), ErrorEnum.CODE_ERROR);
            String password = DigestUtils.md5DigestAsHex((ConfigEnum.PASSWORD_SALT.getValue() + registerDTO.getPassword()).getBytes());
            Long userId = generateId();
            User user = new User()
                    .setUserId(userId)
                    .setUserName(registerDTO.getName())
                    .setPassword(password)
                    .setAvatar(ConfigEnum.DEFAULT_AVATAR.getValue())
                    .setEmail(registerDTO.getEmail())
                    .setCreateTime(LocalDateTime.now())
                    .setSex(registerDTO.getSex())
                    .setPost(PostConstants.DEFAULT_POST.toString())
                    .setRole(PostConstants.user)
                    .setAddress(registerDTO.getAddress());
            userMapper.insert(user);
        } else {
            throw new BusinessException(ErrorEnum.REGISTER_ERROR);
        }

    }

    /**
     * 账户密码登录
     *
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        User user = userMapper.selectByEmail(loginDTO.getEmail());
        String password = DigestUtils.md5DigestAsHex((ConfigEnum.PASSWORD_SALT.getValue() + loginDTO.getPassword()).getBytes());
        //int role = user.getRole();
        if (user == null || !password.equals(user.getPassword())) {
            throw new BusinessException(ErrorEnum.LOGIN_ERROR);
        }
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(user, loginVO);
        String token = JwtUtil.generate(user.getUserId().toString());
        loginVO.setToken(token);
        return loginVO;
    }

    private Long generateId() {
        Snowflake snowflake = new Snowflake();
        return snowflake.nextId();
    }

    private boolean isExist(String email) {
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return true;
        }
        return false;
    }
}
