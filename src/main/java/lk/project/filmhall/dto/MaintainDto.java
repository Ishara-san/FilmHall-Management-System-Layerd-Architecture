package lk.project.filmhall.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MaintainDto {
    private String id;
    private String item;
    private String price;
    private String date;
}
