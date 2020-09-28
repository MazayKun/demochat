package ru.mikheev.kirill.demochat.dao;

import ru.mikheev.kirill.demochat.pojo.MessageModel;
import ru.mikheev.kirill.system.api.model.Message;

import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IMessageDAO {

    int addMessage(Message message);

    List<Message> getMessages();

}
