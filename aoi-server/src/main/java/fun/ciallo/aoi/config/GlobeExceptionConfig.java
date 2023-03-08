package fun.ciallo.aoi.config;

import fun.ciallo.aoi.common.AoiException;
import fun.ciallo.aoi.common.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobeExceptionConfig {
    @ExceptionHandler(AoiException.class)
    public ApiResult<String> aoiExceptionHandler(AoiException e) {
        return ApiResult.STATE(e);
    }

//    @ExceptionHandler(Exception.class)
//    public ApiResult<String> exceptionHandler(Exception e) {
//        ApiResult<String> result = ApiResult.FAILED(null);
//        result.setData(e.getMessage());
//        return result;
//    }
}
