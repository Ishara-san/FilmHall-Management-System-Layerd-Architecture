package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CustomerTm {
    private String id;
    private String name;
    private String genre;
    private String contact;
    private String email;

}
