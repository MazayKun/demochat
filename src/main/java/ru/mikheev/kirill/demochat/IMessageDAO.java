package ru.mikheev.kirill.demochat;

import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface IMessageDAO {

    void addMessage(String author, String message);

    List<MessageModel> getMessages();

}
