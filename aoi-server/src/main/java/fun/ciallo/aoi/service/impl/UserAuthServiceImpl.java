package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.entity.UserAuth;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.mapper.UserAuthMapper;
import fun.ciallo.aoi.mapper.UserProfileMapper;
import fun.ciallo.aoi.service.UserAuthService;
import fun.ciallo.aoi.utils.AssertUtils;
import fun.ciallo.aoi.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {
    @Resource
    private UserProfileMapper userProfileMapper;

    @Override
    public String login(UserAuth userAuth) {
        LambdaQueryWrapper<UserAuth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAuth::getEmail, userAuth.getEmail());
        UserAuth user = baseMapper.selectOne(lambdaQueryWrapper);
        AssertUtils.notNull(user, ApiStatus.USER_AUTH_ERROR);
        LambdaQueryWrapper<UserProfile> userProfileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userProfileLambdaQueryWrapper.eq(UserProfile::getId, user.getProfileId());
        UserProfile profile = userProfileMapper.selectOne(userProfileLambdaQueryWrapper);
        AssertUtils.equals(profile.getStatus(), 1, ApiStatus.USER_BANNED);
        AssertUtils.equals(user.getPassword(), userAuth.getPassword(), ApiStatus.USER_AUTH_ERROR);
        return TokenUtils.tokenGenerator(user.getProfileId());
    }

    @Override
    public boolean existsByEmail(String email) {
        LambdaQueryWrapper<UserAuth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAuth::getEmail, email);
        UserAuth user = baseMapper.selectOne(lambdaQueryWrapper);
        return null != user;
    }
}
