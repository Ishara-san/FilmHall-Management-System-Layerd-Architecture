package lk.project.filmhall.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Salary {
    private String id;
    private String employeeId;
    private String name;
    private int count;
    private String price;
    private String date;
}
