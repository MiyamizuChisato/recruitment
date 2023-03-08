package fun.ciallo.aoi.utils;

import fun.ciallo.aoi.model.MailModel;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtils {
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private MailProperties mailProperties;
    @Resource
    private TemplateEngine templateEngine;

    @SneakyThrows
    public void sendVerifyMail(MailModel mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getTitle());
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mail.getTaker());
        Context context = new Context();
        context.setVariable("code", mail.getParams().get("code"));
        String mailText = templateEngine.process("VerifyMail.html", context);
        helper.setText(mailText, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendResumeMail(MailModel mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getTitle());
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mail.getTaker());
        Context context = new Context();
        context.setVariable("resume", mail.getParams().get("resume"));
        context.setVariable("title", mail.getParams().get("title"));
        String mailText = templateEngine.process("ResumeSendMail.html", context);
        helper.setText(mailText, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendResumePass(MailModel mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(mail.getTitle());
        helper.setFrom(mailProperties.getUsername());
        helper.setTo(mail.getTaker());
        Context context = new Context();
        context.setVariable("title", mail.getParams().get("title"));
        String mailText = templateEngine.process("ResumePassMail.html", context);
        helper.setText(mailText, true);
        mailSender.send(mimeMessage);
    }
}
