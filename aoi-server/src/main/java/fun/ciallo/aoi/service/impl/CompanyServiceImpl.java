package fun.ciallo.aoi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ciallo.aoi.entity.Company;
import fun.ciallo.aoi.mapper.CompanyMapper;
import fun.ciallo.aoi.model.CompanyModel;
import fun.ciallo.aoi.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Override
    public List<CompanyModel> getCompanyByUserId(int id) {
        LambdaQueryWrapper<Company> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Company::getUserId, id);
        lambdaQueryWrapper.eq(Company::getStatus, 1);
        List<Company> list = baseMapper.selectList(lambdaQueryWrapper);
        List<CompanyModel> result = new ArrayList<>();
        list.forEach(item -> {
            CompanyModel model = new CompanyModel();
            BeanUtils.copyProperties(item, model);
            result.add(model);
        });
        return result;
    }
}
