package fun.ciallo.aoi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.entity.Chat;
import fun.ciallo.aoi.entity.Relation;
import fun.ciallo.aoi.service.ChatService;
import fun.ciallo.aoi.service.RelationService;
import fun.ciallo.aoi.utils.UserUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;
    @Resource
    private RelationService relationService;

    @Token
    @GetMapping("/get/unread/{sender}")
    public List<Chat> getUnreadMessage(HttpServletRequest request, @PathVariable int sender) {
        int uid = UserUtils.getIdByRequest(request);
        LambdaQueryWrapper<Chat> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Chat::getReceiver, uid);
        lambdaQueryWrapper.eq(Chat::getSender, sender);
        List<Chat> list = chatService.list(lambdaQueryWrapper);
        chatService.removeBatchByIds(list);
        LambdaQueryWrapper<Relation> relationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        relationLambdaQueryWrapper.eq(Relation::getSender, uid);
        relationLambdaQueryWrapper.eq(Relation::getReceiver, sender);
        Relation one = relationService.getOne(relationLambdaQueryWrapper);
        one.setUnread(0);
        relationService.updateById(one);
        return list;
    }
}
