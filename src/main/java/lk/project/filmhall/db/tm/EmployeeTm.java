package lk.project.filmhall.db.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeTm {
    private String employeeId;
    private String name;
    private String address;
    private String contact;
    private String email;
}
