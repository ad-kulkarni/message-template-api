package api.messagetemplate.controller;

import api.messagetemplate.model.MessageTemplate;
import api.messagetemplate.model.MessageTemplateWrapper;
import api.messagetemplate.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @GetMapping("/api/templates")
    public MessageTemplateWrapper getAllMessageTemplates() {
        List<MessageTemplate> messageTemplates = messageTemplateService.getAllMessageTemplates();

        MessageTemplateWrapper messageTemplateWrapper = new MessageTemplateWrapper();
        messageTemplateWrapper.setMessageTemplates(messageTemplates);

        return messageTemplateWrapper;
    }

    @GetMapping("/api/template/{templateId}")
    public MessageTemplate getMessageTemplate(@PathVariable String templateId) {
        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplate(templateId);

        return messageTemplate;
    }

    @GetMapping("/api/template/{templateId}/compose")
    public MessageTemplate getMessageTemplate(@PathVariable String templateId, @RequestParam Map<String, String> queryParams) {

        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplate(templateId, queryParams);

        return messageTemplate;
    }

    @PostMapping("/api/templates")
    public String saveMessageTemplate(@RequestBody MessageTemplate messageTemplate) {
        messageTemplateService.saveMessageTemplate(messageTemplate);

        return "MessageTemplate created successfully";
    }

    public void setMessageTemplateService(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }
}
