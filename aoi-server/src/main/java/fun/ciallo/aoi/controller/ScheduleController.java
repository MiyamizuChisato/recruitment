package fun.ciallo.aoi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.config.RabbitConfig;
import fun.ciallo.aoi.entity.Position;
import fun.ciallo.aoi.entity.Schedule;
import fun.ciallo.aoi.entity.UserAuth;
import fun.ciallo.aoi.entity.UserProfile;
import fun.ciallo.aoi.model.MailModel;
import fun.ciallo.aoi.model.ScheduleModel;
import fun.ciallo.aoi.model.ScheduleSimpleModel;
import fun.ciallo.aoi.service.PositionService;
import fun.ciallo.aoi.service.ScheduleService;
import fun.ciallo.aoi.service.UserAuthService;
import fun.ciallo.aoi.service.UserProfileService;
import fun.ciallo.aoi.utils.AssertUtils;
import fun.ciallo.aoi.utils.UserUtils;
import fun.ciallo.aoi.vo.ScheduleVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;
    @Resource
    private PositionService positionService;
    @Resource
    private UserAuthService userAuthService;
    @Resource
    private UserProfileService userProfileService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Token
    @PostMapping("/post/schedule")
    private void postSchedule(HttpServletRequest request, @RequestBody ScheduleVo vo) {
        int uid = UserUtils.getIdByRequest(request);
        AssertUtils.equals(vo.getAsker(), uid, ApiStatus.USER_AUTH_ERROR);
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(vo, schedule);
        LambdaQueryWrapper<Schedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Schedule::getAsker, uid);
        lambdaQueryWrapper.eq(Schedule::getPositionId, schedule.getPositionId());
        Schedule one = scheduleService.getOne(lambdaQueryWrapper);
        AssertUtils.isNull(one, ApiStatus.REPEAT_REQUEST);
        scheduleService.save(schedule);
        UserAuth publisher = userAuthService.getById(vo.getPublisher());
        UserProfile profile = userProfileService.getById(uid);
        Position position = positionService.getById(vo.getPositionId());
        MailModel mail = new MailModel();
        mail.setTitle("你收到了一份简历投递");
        mail.setTaker(publisher.getEmail());
        Map<String, Object> data = new HashMap<>();
        data.put("resume", profile.getResume());
        data.put("title", position.getTitle());
        mail.setParams(data);
        rabbitTemplate.convertAndSend(
                RabbitConfig.MAIL_EXCHANGE_NAME,
                RabbitConfig.MAIL_RESUME_ROUTING_KEY_NAME,
                mail
        );
    }

    @Token
    @GetMapping("/get/mine/page/{current}")
    public Page<ScheduleModel> mineSchedule(@PathVariable long current, HttpServletRequest request) {
        int uid = UserUtils.getIdByRequest(request);
        Page<Schedule> page = new Page<>(current, 5);
        return scheduleService.mineSchedule(page, uid);
    }

    @Token
    @GetMapping("/get/page/{positionId}/{current}")
    public Page<ScheduleSimpleModel> pageSchedule(@PathVariable long current, @PathVariable int positionId, HttpServletRequest request) {
        int uid = UserUtils.getIdByRequest(request);
        Page<Schedule> page = new Page<>(current, 5);
        return scheduleService.pageSchedule(page, positionId, uid);
    }

    @Token
    @PutMapping("/put/{scheduleId}/{progress}")
    public void putSchedule(@PathVariable int progress, HttpServletRequest request, @PathVariable int scheduleId) {
        int uid = UserUtils.getIdByRequest(request);
        Schedule schedule = scheduleService.getById(scheduleId);
        AssertUtils.equals(uid, schedule.getPublisher(), ApiStatus.USER_AUTH_ERROR);
        schedule.setProgress(progress);
        scheduleService.updateById(schedule);
        UserProfile profile = userProfileService.getById(schedule.getAsker());
        if (progress == 2 && StringUtils.hasLength(profile.getEmail())) {
            Position position = positionService.getById(schedule.getPositionId());
            MailModel mail = new MailModel();
            mail.setTaker(profile.getEmail());
            mail.setTitle("简历通过筛选啦");
            Map<String, Object> data = new HashMap<>();
            data.put("title", position.getTitle());
            mail.setParams(data);
            rabbitTemplate.convertAndSend(
                    RabbitConfig.MAIL_EXCHANGE_NAME,
                    RabbitConfig.MAIL_RESUME_PASS_ROUTING_KEY_NAME,
                    mail
            );
        }
    }
}
