package fun.ciallo.aoi.config;

import fun.ciallo.aoi.common.ApiStatus;
import fun.ciallo.aoi.common.Token;
import fun.ciallo.aoi.utils.AssertUtils;
import fun.ciallo.aoi.utils.TokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class TokenInterceptorConfig implements WebMvcConfigurer {
    @Bean
    public TokenFilter tokenInterceptor() {
        return new TokenFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.tokenInterceptor())
                .addPathPatterns("/**");
    }
}

class TokenFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Token flag = null;
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            flag = method.getMethodAnnotation(Token.class);
        }
        if (null != flag) {
            String token = request.getHeader("Authorization");
            AssertUtils.notNull(token, ApiStatus.TOKEN_ERROR);
            AssertUtils.isTrue(TokenUtils.tokenValidator(token), ApiStatus.TOKEN_ERROR);
            int id = TokenUtils.tokenParser(token);
            request.setAttribute("id", id);
        }
        return true;
    }
}
