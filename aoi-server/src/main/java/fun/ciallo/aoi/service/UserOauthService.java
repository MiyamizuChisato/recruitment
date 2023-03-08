package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.UserOauth;

public interface UserOauthService extends IService<UserOauth> {
    UserOauth registered(UserOauth userOauth);
}
