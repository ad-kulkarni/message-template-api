package api.messagetemplate.controller;

import api.messagetemplate.model.MessageTemplate;
import api.messagetemplate.model.MessageTemplateWrapper;
import api.messagetemplate.service.MessageTemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MessageTemplateControllerTest {

    private MessageTemplateService messageTemplateService;
    private MessageTemplateController messageTemplateController;

    @Before
    public void setup() {
        messageTemplateService = Mockito.mock(MessageTemplateService.class);
        messageTemplateController = new MessageTemplateController();
        messageTemplateController.setMessageTemplateService(messageTemplateService);
    }

    @Test
    public void testGetAllTemplates() {
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setTemplateId("1");
        messageTemplate1.setTemplateText("Hello, $name!");

        MessageTemplate messageTemplate2 = new MessageTemplate();
        messageTemplate2.setTemplateId("2");
        messageTemplate2.setTemplateText("Hello, I live in $place!");

        List<MessageTemplate> messageTemplates = new ArrayList<>();
        messageTemplates.add(messageTemplate1);
        messageTemplates.add(messageTemplate2);

        Mockito.when(messageTemplateService.getAllMessageTemplates()).thenReturn(messageTemplates);

        MessageTemplateWrapper resultingMessageTemplateWrapper = messageTemplateController.getAllMessageTemplates();

        Assert.assertEquals(2, resultingMessageTemplateWrapper.getMessageTemplates().size());
        Assert.assertEquals("1", resultingMessageTemplateWrapper.getMessageTemplates().get(0).getTemplateId());
        Assert.assertEquals("2", resultingMessageTemplateWrapper.getMessageTemplates().get(1).getTemplateId());
    }
}
