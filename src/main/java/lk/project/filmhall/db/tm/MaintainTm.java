package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MaintainTm {
    private String id;
    private String item;
    private String price;
    private String date;
}
