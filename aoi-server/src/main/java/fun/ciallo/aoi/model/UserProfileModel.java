package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileModel {
    private Integer id;
    private String avatar;
    private String nickname;
    private String email;
    private String motto;
    private String resume;
    private Integer identity;
    private LocalDateTime createTime;
}
