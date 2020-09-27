package ru.mikheev.kirill.demochat;

import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IMessageDAO {

    int addMessage(MessageModel model);

    List<MessageModel> getMessages();

}
