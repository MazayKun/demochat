package ru.mikheev.kirill.demochat;

import org.springframework.beans.factory.annotation.Autowired;
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

    private String addQuery = "";
    private String getQuery = "";

    @Autowired
    public MessageDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
        String sql_st = "CREATE TABLE IF NOT EXISTS messages( \n" +
                "    id SERIAL  PRIMARY KEY, \n" +
                "    author VARCHAR(50) NOT NULL, \n" +
                "    message VARCHAR NOT NULL \n" +
                ");";

        this.getJdbcTemplate().execute(sql_st);
    }

    @Override
    public void addMessage(String author, String message) {
    }

    @Override
    public List<MessageModel> getMessages() {
        String sql = "Select * From messages";
        MessageMapper mapper = new MessageMapper();
        List<MessageModel> list = this.getJdbcTemplate().query(sql, mapper);

        return list;
    }
}
