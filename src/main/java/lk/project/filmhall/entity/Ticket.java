package lk.project.filmhall.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Ticket {
    private String id;
    private String filmId;
    private int count;
    private String price;
    private String date;
}
