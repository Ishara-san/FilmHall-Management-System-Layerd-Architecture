package lk.project.filmhall.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Film {
    private String filmId;
    private String filmName;
    private String genre;
    private String dName;
    private String dContact;
    private String dEmail;
    private String dPrice;
    private String date;
    private String status;
}
