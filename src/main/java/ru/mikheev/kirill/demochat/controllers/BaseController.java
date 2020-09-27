package ru.mikheev.kirill.demochat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.mikheev.kirill.demochat.ApiMessageMapper;
import ru.mikheev.kirill.demochat.IMessageDAO;
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

    private ApiMessageMapper apiMessageMapper;
    private IMessageDAO messageDAO;

    @Autowired
    public BaseController(IMessageDAO messageDAO, ApiMessageMapper apiMessageMapper) {
        this.messageDAO = messageDAO;
        this.apiMessageMapper = apiMessageMapper;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @RequestMapping("/")
    public String getMainPage() {
        return "login";
    }

    @Override
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<>(apiMessageMapper.mapModelListIntoMessageList(messageDAO.getMessages()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> sendMessage(@Valid Message message) {
        int response = messageDAO.addMessage(apiMessageMapper.mapMessageIntoModel(message));
        if(response  == 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
