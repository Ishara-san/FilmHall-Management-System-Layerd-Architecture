package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CheckFilmTm {
    private String filmId;
    private String name;
    private String genre;
    private String status;
}
