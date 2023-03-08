package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.UserAuth;

public interface UserAuthService extends IService<UserAuth> {
    String login(UserAuth userAuth);

    boolean existsByEmail(String email);
}
