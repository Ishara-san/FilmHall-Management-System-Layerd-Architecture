package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CheckFilmDto {
    private String filmId;
    private String filmName;
    private String genre;
    private String status;

}
