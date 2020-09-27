package ru.mikheev.kirill.demochat;

import lombok.*;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class MessageModel {

    private Integer id;
    @NonNull
    private String author;
    @NonNull
    private String message;

}
