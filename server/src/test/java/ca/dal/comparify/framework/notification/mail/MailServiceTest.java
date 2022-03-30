package ca.dal.comparify.framework.notification.mail;

import ca.dal.comparify.framework.notification.mail.MailService;
import ca.dal.comparify.model.HashModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static ca.dal.comparify.utils.DateUtils.zoneNow;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Harsh Shah
 */
@SpringBootTest
@ActiveProfiles("test")
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void testMail() {

        HashModel model = new HashModel();
        model.put("name", "Harsh Shah");
        model.put("alertName", "Grocery");
        model.put("subscriptionDate", zoneNow().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        assertTrue(mailService.send("harshshah1295@gmail.com", "Test",
                "alert-template.html", model));
    }

}