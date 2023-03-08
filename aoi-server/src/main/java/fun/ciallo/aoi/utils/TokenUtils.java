package fun.ciallo.aoi.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    @Value("${aoi.secret}")
    private String secret;
    private static String SECRET;

    @PostConstruct
    public void init() {
        SECRET = secret;
    }

    public static String tokenGenerator(int id) {
        Map<String, Object> payload = new HashMap<>();
        DateTime now = DateTime.now();
        DateTime outTime = now.offsetNew(DateField.YEAR, 3);
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.NOT_BEFORE, now);
        payload.put(JWTPayload.EXPIRES_AT, outTime);
        payload.put("id", id);
        return JWTUtil.createToken(payload, SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean tokenValidator(String token) {
        return JWTUtil.verify(token, SECRET.getBytes());
    }

    public static int tokenParser(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayloads().getInt("id");
    }
}
