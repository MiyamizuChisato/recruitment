package fun.ciallo.aoi.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRegisterVo {
    private String email;
    private String password;
    private String nickname;
    private String code;
}
