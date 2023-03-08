package fun.ciallo.aoi.utils;

import cn.hutool.core.codec.Rot;
import cn.hutool.crypto.SecureUtil;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    public static int getIdByRequest(HttpServletRequest request) {
        return (int) request.getAttribute("id");
    }
    public static String passwordEncode(String password){
        password = Rot.encode13(password);
        return SecureUtil.md5(password);
    }
}
