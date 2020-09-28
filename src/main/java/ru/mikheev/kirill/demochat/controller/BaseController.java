package ru.mikheev.kirill.demochat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.mikheev.kirill.demochat.dao.IMessageDAO;
import ru.mikheev.kirill.system.api.controller.MessagesApi;
import ru.mikheev.kirill.system.api.controller.SendApi;
import ru.mikheev.kirill.system.api.model.Message;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Controller
public class BaseController implements MessagesApi, SendApi {

    private IMessageDAO messageDAO;

    @Autowired
    public BaseController(IMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * Запрос на главную страницу
     * @return основную (и единственную) страницу
     */
    @RequestMapping("/")
    public String getMainPage() {
        return "login";
    }

    /**
     * Запрос на всю историю сообщений
     * @return список всех сообщений беседы
     */
    @Override
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<>(messageDAO.getMessages(), HttpStatus.OK);
    }

    /**
     * В этот запрос приходит сообщение от пользователя
     * @param message объект сообщения
     * @return OK - если сообщение зафиксировано удачно, INTERNAL_SERVER_ERROR - в остальных случаях
     */
    @Override
    public ResponseEntity<Void> sendMessage(@Valid Message message) {
        int response = messageDAO.addMessage(message);
        if(response  == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
