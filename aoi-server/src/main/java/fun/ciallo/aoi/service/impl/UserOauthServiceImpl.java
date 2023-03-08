package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.UserOauth;
import fun.ciallo.aoi.mapper.UserOauthMapper;
import fun.ciallo.aoi.service.UserOauthService;
import org.springframework.stereotype.Service;

@Service
public class UserOauthServiceImpl extends ServiceImpl<UserOauthMapper, UserOauth> implements UserOauthService {
    @Override
    public UserOauth registered(UserOauth userOauth) {
        LambdaQueryWrapper<UserOauth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserOauth::getUuid, userOauth.getUuid());
        lambdaQueryWrapper.eq(UserOauth::getType, userOauth.getType());
        return baseMapper.selectOne(lambdaQueryWrapper);
    }
}
