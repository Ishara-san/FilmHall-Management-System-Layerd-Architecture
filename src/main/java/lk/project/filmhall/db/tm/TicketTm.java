package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class TicketTm {
    private String id;
    private String filmId;
    private int count;
    private String price;
    private String date;
}
