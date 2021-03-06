package api.messagetemplate.repository;

import api.messagetemplate.model.MessageTemplate;
import api.messagetemplate.repository.MessageTemplateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class MessageTemplateRepositoryTest {

    private MessageTemplateRepository messageTemplateRepository;

    @Before
    public void setup() {
        messageTemplateRepository = new MessageTemplateRepository();
    }

    @Test(expected = ResponseStatusException.class)
    public void testMessageTemplateForInvalidId() {
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setTemplateId("1");
        messageTemplate1.setTemplateText("Hello, $name!");

        messageTemplateRepository.saveMessageTemplate(messageTemplate1);

        messageTemplateRepository.getMessageTemplate("2");
    }

    @Test
    public void testGetMessageTemplateWithQueryParams() {
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setTemplateId("1");
        messageTemplate1.setTemplateText("Hello, $name!");

        MessageTemplate messageTemplate2 = new MessageTemplate();
        messageTemplate2.setTemplateId("2");
        messageTemplate2.setTemplateText("Hello, I live in $place!");

        MessageTemplate messageTemplate3 = new MessageTemplate();
        messageTemplate3.setTemplateId("3");
        messageTemplate3.setTemplateText("Hello, $name, $place is very beautiful!");

        messageTemplateRepository.saveMessageTemplate(messageTemplate1);
        messageTemplateRepository.saveMessageTemplate(messageTemplate2);
        messageTemplateRepository.saveMessageTemplate(messageTemplate3);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "John");
        queryParams.put("place", "California");
        queryParams.put("pla", "California");
        queryParams.put("places", "California");

        MessageTemplate resultingTemplate1 = messageTemplateRepository.getMessageTemplateWithQueryParams("1", queryParams);

        Assert.assertEquals("1", resultingTemplate1.getTemplateId());
        Assert.assertEquals("Hello, John!", resultingTemplate1.getTemplateText());

        MessageTemplate resultingTemplate2 = messageTemplateRepository.getMessageTemplateWithQueryParams("2", queryParams);

        Assert.assertEquals("2", resultingTemplate2.getTemplateId());
        Assert.assertEquals("Hello, I live in California!", resultingTemplate2.getTemplateText());

        MessageTemplate resultingTemplate3 = messageTemplateRepository.getMessageTemplateWithQueryParams("3", queryParams);

        Assert.assertEquals("3", resultingTemplate3.getTemplateId());
        Assert.assertEquals("Hello, John, California is very beautiful!", resultingTemplate3.getTemplateText());
    }
}
