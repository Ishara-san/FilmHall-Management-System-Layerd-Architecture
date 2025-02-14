package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class CustomerDto {
    private String id;
    private String name;
    private String genre;
    private String contact;
    private String email;
}
