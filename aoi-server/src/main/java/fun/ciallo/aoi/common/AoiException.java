package fun.ciallo.aoi.common;

import lombok.Getter;

@Getter
public class AoiException extends RuntimeException {
    public AoiException(ApiStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public AoiException message(String message) {
        this.message = message;
        return this;
    }

    private int code;
    private String message;
}
