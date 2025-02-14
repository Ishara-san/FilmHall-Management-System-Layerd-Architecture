package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ShiftDto {
    private String id;
    private String employeeId;
    private String name;
    private int count;
    private String date;
}
