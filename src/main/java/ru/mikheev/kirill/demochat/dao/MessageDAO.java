package ru.mikheev.kirill.demochat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikheev.kirill.demochat.mapper.ApiMessageMapper;
import ru.mikheev.kirill.demochat.mapper.MessageMapper;
import ru.mikheev.kirill.demochat.pojo.MessageModel;
import ru.mikheev.kirill.system.api.model.Message;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Repository
@Transactional
public class MessageDAO extends JdbcDaoSupport implements IMessageDAO {

    /** Шаблон запроса  добавления нового сообщения в базу */
    private String addQuery = "Insert Into messages(author, message) Values (?, ?);";
    /** Запрос всех сообщений из базы */
    private final String getAllQuery = "Select * From messages;";

    private MessageMapper messageMapper;
    private ApiMessageMapper apiMessageMapper;

    /**
     * При создании так же создает таблицу с сообщениями в базе, если таковой не было
     */
    @Autowired
    public MessageDAO(DataSource dataSource, MessageMapper messageMapper, ApiMessageMapper apiMessageMapper) {
        this.setDataSource(dataSource);
        this.messageMapper = messageMapper;
        this.apiMessageMapper = apiMessageMapper;
        initMessageTable();
    }

    /**
     * Добавление новог сообщения в базу
     * @param message
     * @return 0 - если добавлено успешно, иначе 1
     */
    @Override
    public int addMessage(Message message) {
        MessageModel model = apiMessageMapper.mapMessageIntoModel(message);
        try {
            this.getJdbcTemplate().update(addQuery, model.getAuthor(), model.getMessage());
            return 0;
        }catch (DataAccessException e) {
            System.out.println(e.toString());
            return 1;
        }
    }

    /**
     * Запрос на все ообщения из таблицы
     * @return список сообщений
     */
    @Override
    public List<Message> getMessages() {
        List<MessageModel> messageModels = this.getJdbcTemplate().query(getAllQuery, messageMapper);
        return apiMessageMapper.mapModelListIntoMessageList(messageModels);
    }

    /**
     * Создает таблицу сообщений в базе
     */
    private void initMessageTable() {
        String sql_st = "CREATE TABLE IF NOT EXISTS messages( \n" +
                "    id SERIAL  PRIMARY KEY, \n" +
                "    author VARCHAR(50) NOT NULL, \n" +
                "    message VARCHAR NOT NULL \n" +
                ");";

        this.getJdbcTemplate().execute(sql_st);
    }
}
