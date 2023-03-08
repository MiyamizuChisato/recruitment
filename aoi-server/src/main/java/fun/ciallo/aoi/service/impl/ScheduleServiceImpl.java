package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Position;
import fun.ciallo.aoi.entity.Schedule;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.mapper.PositionMapper;
import fun.ciallo.aoi.mapper.ScheduleMapper;
import fun.ciallo.aoi.mapper.UserProfileMapper;
import fun.ciallo.aoi.model.*;
import fun.ciallo.aoi.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private UserProfileMapper userProfileMapper;

    @Override
    public Page<ScheduleSimpleModel> pageSchedule(Page<Schedule> page, int positionId, int uid) {
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getPositionId, positionId);
        lambdaQueryWrapper.eq(Schedule::getPublisher, uid);
        lambdaQueryWrapper.eq(Schedule::getProgress, 1);
        lambdaQueryWrapper.orderByDesc(Schedule::getProgress);
        baseMapper.selectPage(page, lambdaQueryWrapper);
        List<ScheduleSimpleModel> records = new ArrayList<>();
        page.getRecords().forEach(item -> records.add(this.build(item)));
        Page<ScheduleSimpleModel> result = new Page<>();
        BeanUtils.copyProperties(page, result);
        result.setRecords(records);
        return result;
    }

    @Override
    public Page<ScheduleModel> mineSchedule(Page<Schedule> page, int uid) {
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getAsker, uid);
        lambdaQueryWrapper.orderByDesc(Schedule::getProgress);
        baseMapper.selectPage(page, lambdaQueryWrapper);
        List<ScheduleModel> records = new ArrayList<>();
        page.getRecords().forEach(item -> {
            ScheduleModel model = new ScheduleModel();
            BeanUtils.copyProperties(item, model);
            UserProfile profile = userProfileMapper.selectById(item.getPublisher());
            UserSimpleModel userSimpleModel = new UserSimpleModel();
            BeanUtils.copyProperties(profile, userSimpleModel);
            model.setUser(userSimpleModel);
            Position position = positionMapper.selectById(item.getPositionId());
            PositionModel positionModel = new PositionModel();
            BeanUtils.copyProperties(position, positionModel);
            model.setPosition(positionModel);
            records.add(model);
        });
        Page<ScheduleModel> result = new Page<>();
        BeanUtils.copyProperties(page, result);
        result.setRecords(records);
        return result;
    }

    private ScheduleSimpleModel build(Schedule schedule) {
        ScheduleSimpleModel model = new ScheduleSimpleModel();
        BeanUtils.copyProperties(schedule, model);
        UserProfile profile = userProfileMapper.selectById(schedule.getAsker());
        UserProfileModel userProfileModel = new UserProfileModel();
        BeanUtils.copyProperties(profile, userProfileModel);
        model.setUser(userProfileModel);
        return model;
    }
}
