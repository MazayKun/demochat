package ru.mikheev.kirill.demochat;

import org.springframework.stereotype.Service;
import ru.mikheev.kirill.system.api.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Service
public class ApiMessageMapper {
    public List<Message> mapModelListIntoMessageList(List<MessageModel> models) {
        List<Message> answer = new ArrayList<>();
        for (MessageModel mm : models) {
            Message message = new Message();
            message.setId(mm.getId());
            message.setAuthor(mm.getAuthor());
            message.setMessage(mm.getMessage());
            answer.add(message);
        }
        return answer;
    }

    public MessageModel mapMessageIntoModel(Message message) {
        return new MessageModel(message.getAuthor(), message.getMessage());
    }
}
