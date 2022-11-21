package cn.ist.lowcoding.common.util;

import cn.ist.lowcoding.common.model.Role;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;

/**
 * 请求工具类，可以直接通过全局的Request获取到在JWTInterceptor中嵌入的用户信息
 */
public class RequestUtil {
    /**
     * 获取请求的某个属性
     *
     * @param name
     *            属性名称
     * @return
     */
    private static Object getAttribute(String name) {
        return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest()
            .getAttribute(name);
    }

    public static List<Role> getRoles() {
        return (List<Role>) getAttribute("roles");
    }

    public static String getUserId() {
        return (String) getAttribute("userId");
    }

    public static String getUsername() { return getUserField("username"); }

    public static String getPositionId() { return getUserField("positionId"); }

    public static String getPositionName() { return getUserField("positionName"); }

    public static String getDepartmentId() { return getUserField("departmentId"); }

    public static String getDepartmentName() { return getUserField("departmentName"); }

    public static Map<String, String> getUserFields() { return (Map<String, String>) getAttribute("userFields"); }

    public static String getUserField(String key) { return getUserFields().get(key); }

    public static String getToken() { return (String) getAttribute("token"); }
}
