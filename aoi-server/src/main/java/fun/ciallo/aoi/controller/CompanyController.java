package fun.ciallo.aoi.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.entity.Company;
import fun.ciallo.aoi.model.CompanyModel;
import fun.ciallo.aoi.service.CompanyService;
import fun.ciallo.aoi.utils.UserUtils;
import fun.ciallo.aoi.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;
    @Resource
    private FileStorageService fileStorageService;

    @Token
    @PostMapping("/post/company")
    public void postCompany(
            HttpServletRequest request,
            @RequestPart("logo") MultipartFile logo,
            @RequestPart("appendix") MultipartFile appendix,
            @RequestPart("vo") CompanyVo vo) {
        FileInfo logoInfo = fileStorageService.of(logo).upload();
        FileInfo appendixInfo = fileStorageService.of(appendix).upload();
        int id = UserUtils.getIdByRequest(request);
        Company company = new Company();
        BeanUtils.copyProperties(vo, company);
        company.setUserId(id);
        company.setLogo(logoInfo.getUrl());
        company.setAppendix(appendixInfo.getUrl());
        companyService.save(company);
    }

    @Token
    @GetMapping("/get/company")
    public List<CompanyModel> getCompany(HttpServletRequest request) {
        int id = UserUtils.getIdByRequest(request);
        return companyService.getCompanyByUserId(id);
    }
}
