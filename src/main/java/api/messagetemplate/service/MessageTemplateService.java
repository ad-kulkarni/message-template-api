package api.messagetemplate.service;

import api.messagetemplate.model.MessageTemplate;
import api.messagetemplate.repository.MessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageTemplateService {

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    public List<MessageTemplate> getAllMessageTemplates() {
        return messageTemplateRepository.getAllMessageTemplates();
    }

    public MessageTemplate getMessageTemplate(String templateId) {
        return messageTemplateRepository.getMessageTemplate(templateId);
    }

    public MessageTemplate getMessageTemplate(String templateId, Map<String, String> queryParams) {
        return messageTemplateRepository.getMessageTemplate(templateId, queryParams);
    }

    public void saveMessageTemplate(MessageTemplate messageTemplate) {
        messageTemplateRepository.saveMessageTemplate(messageTemplate);
    }
}
