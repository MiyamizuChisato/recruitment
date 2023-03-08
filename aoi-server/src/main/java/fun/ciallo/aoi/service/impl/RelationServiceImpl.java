package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Relation;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.mapper.RelationMapper;
import fun.ciallo.aoi.mapper.UserProfileMapper;
import fun.ciallo.aoi.model.RelationModel;
import fun.ciallo.aoi.model.UserSimpleModel;
import fun.ciallo.aoi.service.RelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements RelationService {
    @Resource
    private UserProfileMapper userProfileMapper;

    @Override
    public Page<RelationModel> getReceivers(Page<Relation> page, int uid) {
        LambdaQueryWrapper<Relation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Relation::getSender, uid);
        baseMapper.selectPage(page, lambdaQueryWrapper);
        List<RelationModel> records = new ArrayList<>();
        page.getRecords().forEach(item -> {
            RelationModel model = new RelationModel();
            BeanUtils.copyProperties(item, model);
            UserProfile profile = userProfileMapper.selectById(item.getReceiver());
            UserSimpleModel user = new UserSimpleModel();
            BeanUtils.copyProperties(profile, user);
            model.setReceiver(user);
            records.add(model);
        });
        Page<RelationModel> result = new Page<>();
        BeanUtils.copyProperties(page, result);
        result.setRecords(records);
        return result;
    }
}
