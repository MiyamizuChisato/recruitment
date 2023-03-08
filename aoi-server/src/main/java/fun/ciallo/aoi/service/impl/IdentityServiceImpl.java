package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Identity;
import fun.ciallo.aoi.mapper.IdentityMapper;
import fun.ciallo.aoi.service.IdentityService;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl extends ServiceImpl<IdentityMapper, Identity> implements IdentityService {
}
