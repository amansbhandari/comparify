package ca.dal.comparify.framework.mail;

import ca.dal.comparify.model.HashModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Harsh Shah
 */
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.name}")
    private String name;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public boolean send(String to, String subject, String template, HashModel model) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();

            for (Map.Entry<String, Object> entry : model.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }

            helper.setFrom(new InternetAddress(fromEmail, name));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateEngine.process(template, context),
                    true);

            emailSender.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
