package fun.ciallo.aoi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.entity.Relation;
import fun.ciallo.aoi.model.RelationModel;
import fun.ciallo.aoi.service.RelationService;
import fun.ciallo.aoi.utils.AssertUtils;
import fun.ciallo.aoi.utils.UserUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/relation")
public class RelationController {
    @Resource
    private RelationService relationService;

    @Token
    @GetMapping("/get/receivers/page/{current}")
    public Page<RelationModel> getReceivers(@PathVariable int current, HttpServletRequest request) {
        int uid = UserUtils.getIdByRequest(request);
        Page<Relation> page = new Page<>(current, 6);
        return relationService.getReceivers(page, uid);
    }

    @Token
    @PostMapping("/validate/sender/{sender}/receiver/{receiver}")
    public void validateRelation(HttpServletRequest request, @PathVariable int receiver, @PathVariable int sender) {
        AssertUtils.notEquals(sender, receiver, ApiStatus.CHAT_SELF);
        int uid = UserUtils.getIdByRequest(request);
        AssertUtils.equals(uid, sender, ApiStatus.USER_AUTH_ERROR);
        LambdaQueryWrapper<Relation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Relation::getSender, sender);
        lambdaQueryWrapper.eq(Relation::getReceiver, receiver);
        Relation one = relationService.getOne(lambdaQueryWrapper);
        if (null == one) {
            Relation send = new Relation();
            send.setSender(sender);
            send.setReceiver(receiver);
            Relation receive = new Relation();
            receive.setSender(receiver);
            receive.setReceiver(sender);
            relationService.save(send);
            relationService.save(receive);
        }
    }
}
