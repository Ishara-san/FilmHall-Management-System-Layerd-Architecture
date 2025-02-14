package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class SalaryDto {
    private String id;
    private String employeeId;
    private String name;
    private int count;
    private String price;
    private String date;
}
