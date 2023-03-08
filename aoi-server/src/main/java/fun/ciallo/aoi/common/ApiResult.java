package fun.ciallo.aoi.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T> {
    private T data;
    private int code;
    private String message;
    private final long timestamp = System.currentTimeMillis();

    public ApiResult<T> code(int code) {
        this.setCode(code);
        return this;
    }

    public ApiResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public ApiResult<T> data(T data) {
        this.setData(data);
        return this;
    }

    public static <T> ApiResult<T> STATE(ApiStatus status) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage());
        return result;
    }

    public static <T> ApiResult<T> SUCCESS(T data) {
        ApiResult<T> result = ApiResult.STATE(ApiStatus.SUCCESS);
        result.data(data);
        return result;
    }

    public static <T> ApiResult<T> SUCCESS() {
        return ApiResult.STATE(ApiStatus.SUCCESS);
    }

    public static <T> ApiResult<T> FAILED(T data) {
        ApiResult<T> result = ApiResult.STATE(ApiStatus.FAILED);
        result.data(data);
        return result;
    }

    public static <T> ApiResult<T> FAILED() {
        return ApiResult.STATE(ApiStatus.FAILED);
    }

    public static ApiResult<String> STATE(AoiException exception) {
        ApiResult<String> result = new ApiResult<>();
        result.setCode(exception.getCode());
        result.setMessage(exception.getMessage());
        return result;
    }
}
