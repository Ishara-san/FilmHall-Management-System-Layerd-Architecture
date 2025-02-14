package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class UserDto {
    private String name;
    private String password;
    private String status;
}
