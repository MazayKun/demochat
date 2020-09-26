package ru.mikheev.kirill.demochat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@AllArgsConstructor
@Getter
@Setter
public class MessageModel {

    private Integer id;
    private String author;
    private String message;

}
