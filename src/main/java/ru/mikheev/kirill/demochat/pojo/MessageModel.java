package ru.mikheev.kirill.demochat.pojo;

import lombok.*;

/**
 * Модель, на которую мапятся записи из таблицы сообщений в базе данных
 * @author Kirill Mikheev
 * @version 1.0
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageModel {

    private Integer id;
    @NonNull
    private String author;
    @NonNull
    private String message;

}
