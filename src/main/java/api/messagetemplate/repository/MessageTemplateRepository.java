package api.messagetemplate.repository;

import api.messagetemplate.model.MessageTemplate;
import api.messagetemplate.util.StrUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class MessageTemplateRepository {

    private Logger logger = LogManager.getLogger(MessageTemplateRepository.class);

    private static Map<String, String> messageMap;
    static {
        messageMap = new HashMap<>();
    }

    public List<MessageTemplate> getAllMessageTemplates() {
        List<MessageTemplate> messageTemplates = new ArrayList<>();
        for(Map.Entry<String, String> entry : messageMap.entrySet()) {
            MessageTemplate messageTemplate = new MessageTemplate();
            messageTemplate.setTemplateId(entry.getKey());
            messageTemplate.setTemplateText(entry.getValue());
            messageTemplates.add(messageTemplate);
        }

        return messageTemplates;
    }

    public MessageTemplate getMessageTemplate(String templateId) {
        if(messageMap.containsKey(templateId)) {
            MessageTemplate messageTemplate = new MessageTemplate();
            messageTemplate.setTemplateId(templateId);
            messageTemplate.setTemplateText(messageMap.get(templateId));
            return messageTemplate;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MessageTemplate with id " + templateId + " not found!");
    }

    public MessageTemplate getMessageTemplateWithQueryParams(String templateId, Map<String, String> queryParams) {
        if(messageMap.containsKey(templateId)) {
            String templateText = messageMap.get(templateId);

            for(Map.Entry<String, String> entry : queryParams.entrySet()) {
                if(StrUtils.isTokenWordPresent(templateText, entry.getKey())) {
                    templateText = templateText.replaceAll(StrUtils.getEscapedTokenWord(entry.getKey()), entry.getValue());
                }
            }
            logger.debug(templateText);

            MessageTemplate messageTemplate = new MessageTemplate();
            messageTemplate.setTemplateId(templateId);
            messageTemplate.setTemplateText(templateText);
            return messageTemplate;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MessageTemplate with id " + templateId + " not found!");
    }

    public void saveMessageTemplate(MessageTemplate messageTemplate) {
        if(messageMap.containsKey(messageTemplate.getTemplateId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "MessageTemplate with id " + messageTemplate.getTemplateId() + " exists. Please choose another template id!");
        }
        messageMap.put(messageTemplate.getTemplateId(), messageTemplate.getTemplateText());
    }
}
