package ru.mikheev.kirill.demochat;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Component
public class MessageMapper implements RowMapper<MessageModel> {
    @Override
    public MessageModel mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("id");
        String author = resultSet.getString("author");
        String message = resultSet.getString("message");
        return new MessageModel(id, author, message);
    }
}
