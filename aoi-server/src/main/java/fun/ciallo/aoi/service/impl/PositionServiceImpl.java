package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Company;
import fun.ciallo.aoi.entity.Position;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.mapper.CompanyMapper;
import fun.ciallo.aoi.mapper.PositionMapper;
import fun.ciallo.aoi.mapper.UserProfileMapper;
import fun.ciallo.aoi.model.*;
import fun.ciallo.aoi.service.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private UserProfileMapper userProfileMapper;
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public List<PositionManageModel> getManagePosition(int uid) {
        LambdaQueryWrapper<Position> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Position::getUserId, uid);
        List<Position> positions = baseMapper.selectList(lambdaQueryWrapper);
        List<PositionManageModel> result = new ArrayList<>();
        positions.forEach(item -> {
            Company company = companyMapper.selectById(item.getCompanyId());
            PositionManageModel model = new PositionManageModel();
            BeanUtils.copyProperties(item, model);
            model.setCompany(company.getName());
            result.add(model);
        });
        return result;
    }

    @Override
    public Page<PositionCardModel> pagePositionCard(Page<Position> page) {
        LambdaQueryWrapper<Position> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Position::getStatus, 1);
        lambdaQueryWrapper.orderByDesc(Position::getCreateTime);
        return build(page, lambdaQueryWrapper);
    }


    @Override
    public Page<PositionCardModel> search(Page<Position> page, String keyword) {
        LambdaQueryWrapper<Position> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Position::getStatus, 1);
        lambdaQueryWrapper.orderByDesc(Position::getHot);
        lambdaQueryWrapper.like(Position::getTitle, keyword);
        lambdaQueryWrapper.or();
        lambdaQueryWrapper.like(Position::getContent, keyword);
        return build(page, lambdaQueryWrapper);
    }

    @Override
    public List<PositionCardModel> carousel() {
        LambdaQueryWrapper<Position> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Position::getHot);
        lambdaQueryWrapper.last("limit 8");
        List<Position> positions = baseMapper.selectList(lambdaQueryWrapper);
        List<PositionCardModel> result = new ArrayList<>();
        positions.forEach(item -> {
            PositionCardModel model = new PositionCardModel();
            BeanUtils.copyProperties(item, model);
            this.build(model, item);
            result.add(model);
        });
        return result;
    }

    private Page<PositionCardModel> build(Page<Position> page, LambdaQueryWrapper<Position> lambdaQueryWrapper) {
        baseMapper.selectPage(page, lambdaQueryWrapper);
        Page<PositionCardModel> result = new Page<>();
        List<PositionCardModel> records = new ArrayList<>();
        BeanUtils.copyProperties(page, result);
        page.getRecords().forEach(item -> {
            PositionCardModel model = new PositionCardModel();
            BeanUtils.copyProperties(item, model);
            this.build(model, item);
            records.add(model);
        });
        result.setRecords(records);
        return result;
    }

    @Override
    public PositionCardModel build(PositionCardModel model, Position position) {
        UserProfile userProfile = userProfileMapper.selectById(position.getUserId());
        UserSimpleModel userSimpleModel = new UserSimpleModel();
        BeanUtils.copyProperties(userProfile, userSimpleModel);
        model.setUser(userSimpleModel);
        Company company = companyMapper.selectById(position.getCompanyId());
        CompanyModel companyModel = new CompanyModel();
        BeanUtils.copyProperties(company, companyModel);
        model.setCompany(companyModel);
        return model;
    }

    @Override
    public PositionModel build(PositionModel model, Position position) {
        UserProfile userProfile = userProfileMapper.selectById(position.getUserId());
        UserSimpleModel userSimpleModel = new UserSimpleModel();
        BeanUtils.copyProperties(userProfile, userSimpleModel);
        model.setUser(userSimpleModel);
        Company company = companyMapper.selectById(position.getCompanyId());
        CompanyModel companyModel = new CompanyModel();
        BeanUtils.copyProperties(company, companyModel);
        model.setCompany(companyModel);
        return model;
    }
}
