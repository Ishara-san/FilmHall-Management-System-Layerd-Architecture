package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class filmTableTm {
    private String filmId;
    private String name;
    private String genre;
    private String dName;
    private String dPrice;
    private int count;
    private String income;
    private String dContact;
    private String dEmail;
    private String date;
    private String status;
}
