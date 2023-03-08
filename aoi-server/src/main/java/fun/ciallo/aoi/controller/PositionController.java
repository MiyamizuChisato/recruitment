package fun.ciallo.aoi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.entity.Position;
import fun.ciallo.aoi.model.PositionCardModel;
import fun.ciallo.aoi.model.PositionManageModel;
import fun.ciallo.aoi.model.PositionModel;
import fun.ciallo.aoi.service.CompanyService;
import fun.ciallo.aoi.service.PositionService;
import fun.ciallo.aoi.service.UserProfileService;
import fun.ciallo.aoi.utils.UserUtils;
import fun.ciallo.aoi.vo.PositionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Resource
    private PositionService positionService;
    @Resource
    private CompanyService companyService;
    @Resource
    private UserProfileService userProfileService;

    @Token
    @PostMapping("/post/position")
    public int postPosition(@RequestBody PositionVo vo, HttpServletRequest request) {
        Position position = new Position();
        BeanUtils.copyProperties(vo, position);
        int uid = UserUtils.getIdByRequest(request);
        position.setUserId(uid);
        positionService.save(position);
        return position.getId();
    }

    @Token
    @GetMapping("/get/manage/positions")
    public List<PositionManageModel> getManagePositions(HttpServletRequest request) {
        int uid = UserUtils.getIdByRequest(request);
        return positionService.getManagePosition(uid);
    }

    @GetMapping("/get/{id}")
    public PositionModel getPositionById(@PathVariable int id) {
        Position position = positionService.getById(id);
        position.setHot(position.getHot() + 1);
        positionService.updateById(position);
        PositionModel model = new PositionModel();
        BeanUtils.copyProperties(position, model);
        positionService.build(model, position);
        return model;
    }

    @GetMapping("/get/page/{current}")
    public Page<PositionCardModel> getPositionByPage(@PathVariable long current) {
        Page<Position> page = new Page<>(current, 12);
        return positionService.pagePositionCard(page);
    }

    @GetMapping("/get/{keyword}/{current}")
    public Page<PositionCardModel> search(@PathVariable long current, @PathVariable String keyword) {
        Page<Position> page = new Page<>(current, 12);
        return positionService.search(page, keyword);
    }

    @GetMapping("/get/carousel")
    public List<PositionCardModel> carousel() {
        return positionService.carousel();
    }

    @Token
    @DeleteMapping("/delete/{id}")
    public void removePosition(HttpServletRequest request, @PathVariable int id) {
        int uid = UserUtils.getIdByRequest(request);
        LambdaQueryWrapper<Position> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Position::getId, id);
        lambdaQueryWrapper.eq(Position::getUserId, uid);
        positionService.remove(lambdaQueryWrapper);
    }
}
