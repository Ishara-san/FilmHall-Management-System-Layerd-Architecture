package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ShiftTm {
    private String id;
    private String employeeId;
    private String name;
    private int count;
    private String date;
}
