package ru.mikheev.kirill.demochat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Repository
@Transactional
public class MessageDAO extends JdbcDaoSupport implements IMessageDAO {

    private String addQuery = "Insert Into messages(author, message) Values (?, ?);";
    private final String getAllQuery = "Select * From messages;";

    private MessageMapper messageMapper;

    @Autowired
    public MessageDAO(DataSource dataSource, MessageMapper messageMapper) {
        this.setDataSource(dataSource);
        this.messageMapper = messageMapper;
        String sql_st = "CREATE TABLE IF NOT EXISTS messages( \n" +
                "    id SERIAL  PRIMARY KEY, \n" +
                "    author VARCHAR(50) NOT NULL, \n" +
                "    message VARCHAR NOT NULL \n" +
                ");";

        this.getJdbcTemplate().execute(sql_st);
    }

    @Override
    public int addMessage(MessageModel model) {
        try {
            this.getJdbcTemplate().update(addQuery, model.getAuthor(), model.getMessage());
            return 0;
        }catch (DataAccessException e) {
            System.out.println(e.toString());
            return 1;
        }
    }

    @Override
    public List<MessageModel> getMessages() {
        return this.getJdbcTemplate().query(getAllQuery, messageMapper);
    }
}
