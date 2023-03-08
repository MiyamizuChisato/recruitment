package fun.ciallo.aoi.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.model.UserProfileModel;
import fun.ciallo.aoi.model.UserSimpleModel;
import fun.ciallo.aoi.service.IdentityService;
import fun.ciallo.aoi.service.UserProfileService;
import fun.ciallo.aoi.utils.UserUtils;
import fun.ciallo.aoi.vo.UserProfileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserProfileController {
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private FileStorageService fileStorageService;
    @Resource
    private IdentityService identityService;

    @Token
    @GetMapping("/get/profile")
    public UserProfileModel getProfile(HttpServletRequest request) {
        int id = UserUtils.getIdByRequest(request);
        UserProfile userProfile = userProfileService.getById(id);
        UserProfileModel model = new UserProfileModel();
        BeanUtils.copyProperties(userProfile, model);
        return model;
    }

    @Token
    @PutMapping("/put/avatar")
    public String putAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {
        FileInfo fileInfo = fileStorageService.of(avatar)
                .image(img -> img.size(200, 200))
                .upload();
        int id = UserUtils.getIdByRequest(request);
        UserProfile profile = new UserProfile();
        profile.setId(id);
        profile.setAvatar(fileInfo.getUrl());
        userProfileService.updateById(profile);
        return profile.getAvatar();
    }

    @Token
    @PutMapping("/put/resume")
    public String putResume(HttpServletRequest request, @RequestParam("resume") MultipartFile resume) {
        FileInfo fileInfo = fileStorageService.of(resume)
                .upload();
        int id = UserUtils.getIdByRequest(request);
        UserProfile profile = new UserProfile();
        profile.setId(id);
        profile.setResume(fileInfo.getUrl());
        userProfileService.updateById(profile);
        return profile.getResume();
    }

    @Token
    @PutMapping("/put/profile")
    public void putProfile(HttpServletRequest request, @RequestBody UserProfileVo vo) {
        int id = UserUtils.getIdByRequest(request);
        UserProfile profile = new UserProfile();
        BeanUtils.copyProperties(vo, profile);
        profile.setId(id);
        userProfileService.updateById(profile);
    }

    @GetMapping("/get/simple/{id}")
    public UserSimpleModel getSimple(@PathVariable int id) {
        UserProfile profile = userProfileService.getById(id);
        UserSimpleModel model = new UserSimpleModel();
        BeanUtils.copyProperties(profile, model);
        return model;
    }
}
