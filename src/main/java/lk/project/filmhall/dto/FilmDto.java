package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class FilmDto {
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
