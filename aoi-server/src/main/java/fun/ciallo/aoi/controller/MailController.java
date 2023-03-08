package fun.ciallo.aoi.controller;

import cn.hutool.captcha.generator.RandomGenerator;
import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.config.RabbitConfig;
import fun.ciallo.aoi.model.MailModel;
import fun.ciallo.aoi.service.UserAuthService;
import fun.ciallo.aoi.utils.AssertUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private UserAuthService userAuthService;

    @GetMapping("/verify/{email}")
    public String sendVerifyCode(@PathVariable String email) {
        AssertUtils.notTrue(userAuthService.existsByEmail(email), ApiStatus.USER_AUTH_ERROR);
        String key = "verify::" + email;
        redisTemplate.delete(key);
        String code = new RandomGenerator("0123456789", 6).generate();
        redisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
        MailModel mail = new MailModel();
        mail.setTaker(email);
        mail.setTitle("AOI 用户注册");
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        mail.setParams(map);
        rabbitTemplate.convertAndSend(
                RabbitConfig.MAIL_EXCHANGE_NAME,
                RabbitConfig.MAIL_VERIFY_ROUTING_KEY_NAME,
                mail
        );
        return "验证码发送成功，请检查你的收件箱。";
    }
}
