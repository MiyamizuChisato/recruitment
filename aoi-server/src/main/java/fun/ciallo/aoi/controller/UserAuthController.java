package fun.ciallo.aoi.controller;

import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.entity.UserAuth;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.service.UserAuthService;
import fun.ciallo.aoi.service.UserProfileService;
import fun.ciallo.aoi.utils.AssertUtils;
import fun.ciallo.aoi.utils.TokenUtils;
import fun.ciallo.aoi.utils.UserUtils;
import fun.ciallo.aoi.vo.UserAuthLoginVo;
import fun.ciallo.aoi.vo.UserAuthRegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/register")
    public String register(@RequestBody UserAuthRegisterVo vo) {
        // TODO 验证码校验
        String key = "verify::" + vo.getEmail();
        String code = (String) redisTemplate.opsForValue().get(key);
        AssertUtils.notNull(code, ApiStatus.USER_AUTH_ERROR);
        AssertUtils.equals(code, vo.getCode(), ApiStatus.USER_AUTH_ERROR);
        UserAuth userAuth = new UserAuth();
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(vo, userProfile);
        userProfileService.save(userProfile);
        BeanUtils.copyProperties(vo, userAuth);
        userAuth.setPassword(UserUtils.passwordEncode(userAuth.getPassword()));
        userAuth.setProfileId(userProfile.getId());
        userAuthService.save(userAuth);
        redisTemplate.delete(key);
        return TokenUtils.tokenGenerator(userProfile.getId());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAuthLoginVo vo) {
        UserAuth userAuth = new UserAuth();
        BeanUtils.copyProperties(vo, userAuth);
        userAuth.setPassword(UserUtils.passwordEncode(userAuth.getPassword()));
        return userAuthService.login(userAuth);
    }
}
