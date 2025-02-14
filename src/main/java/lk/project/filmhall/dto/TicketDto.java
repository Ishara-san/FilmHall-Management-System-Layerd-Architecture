package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class TicketDto {
    private String id;
    private String filmId;
    private int count;
    private String price;
    private String date;
}
