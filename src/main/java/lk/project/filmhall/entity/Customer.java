package lk.project.filmhall.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Customer {
    private String id;
    private String name;
    private String genre;
    private String contact;
    private String email;
}
