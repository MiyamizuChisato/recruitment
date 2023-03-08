package fun.ciallo.aoi.amqp;

import fun.ciallo.aoi.config.RabbitConfig;
import fun.ciallo.aoi.model.MailModel;
import fun.ciallo.aoi.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RabbitMQ {
    @Resource
    private MailUtils mailUtils;

    @RabbitListener(queues = RabbitConfig.MAIL_VERIFY_QUEUE_NAME)
    public void emailVerify(MailModel mail) throws MessagingException {
        log.info("接收到验证码消息任务，向[{}]------>发送验证码", mail.getTaker());
        mailUtils.sendVerifyMail(mail);
    }

    @RabbitListener(queues = RabbitConfig.MAIL_RESUME_QUEUE_NAME)
    public void resume(MailModel mail) throws MessagingException {
        log.info("接收到简历投递任务，向[{}]------>发送邮件", mail.getTaker());
        mailUtils.sendResumeMail(mail);
    }

    @RabbitListener(queues = RabbitConfig.MAIL_RESUME_PASS_QUEUE_NAME)
    public void resumePass(MailModel mail) throws MessagingException {
        log.info("接收到简历通过任务，向[{}]------>发送邮件", mail.getTaker());
        mailUtils.sendResumePass(mail);
    }
}
