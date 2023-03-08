package fun.ciallo.aoi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiStatus {
    SUCCESS(0, ""),
    FAILED(-1, ""),
    OAUTH_TIME_OUT(-2, "认证超时，请稍后重试"),
    TOKEN_ERROR(-3, "无法请求这个资源"),
    USER_AUTH_ERROR(-4, "用户认证错误"),
    USER_BANNED(-5, "用户被禁用"),
    REPEAT_REQUEST(-6, "重复投递简历"),
    CHAT_SELF(-7, "错误的聊天对象");

    private final int code;
    private final String message;
}
