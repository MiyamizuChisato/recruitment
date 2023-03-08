package fun.ciallo.aoi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.aoi.common.ApiResult;
import fun.ciallo.aoi.common.Result;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@RestControllerAdvice(basePackages = "fun.ciallo.aoi.controller")
public class ApiResulConfig implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !(returnType.hasMethodAnnotation(Result.class) || returnType.getParameterType().isAssignableFrom(ApiResult.class));
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(ApiResult.SUCCESS(body));
        }
        return ApiResult.SUCCESS(body);
    }
}
