package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.Company;
import fun.ciallo.aoi.model.CompanyModel;

import java.util.List;

public interface CompanyService extends IService<Company> {
    List<CompanyModel> getCompanyByUserId(int id);
}
