package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.mapper.UserProfileMapper;
import fun.ciallo.aoi.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {
}
