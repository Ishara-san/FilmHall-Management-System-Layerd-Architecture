package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString


public class SalaryTm {
    private String id;
    private String employeeId;
    private String name;
    private int count;
    private String price;
    private String date;
}
