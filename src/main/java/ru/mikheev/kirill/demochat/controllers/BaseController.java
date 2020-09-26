package ru.mikheev.kirill.demochat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mikheev.kirill.demochat.ApiMessageMapper;
import ru.mikheev.kirill.demochat.IMessageDAO;
import ru.mikheev.kirill.demochat.MessageModel;
import ru.mikheev.kirill.system.api.controller.MessagesApi;
import ru.mikheev.kirill.system.api.model.Message;

import java.util.List;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@Controller
public class BaseController implements MessagesApi {

    private ApiMessageMapper apiMessageMapper;
    private IMessageDAO messageDAO;

    @Autowired
    public BaseController(IMessageDAO messageDAO, ApiMessageMapper apiMessageMapper) {
        this.messageDAO = messageDAO;
        this.apiMessageMapper = apiMessageMapper;
    }

    @RequestMapping("/login")
    public String kisa() {
        return "login";
    }

    @Override
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<>(apiMessageMapper.map(messageDAO.getMessages()), HttpStatus.OK);
    }
}
