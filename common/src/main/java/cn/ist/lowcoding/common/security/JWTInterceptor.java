package cn.ist.lowcoding.common.security;

import cn.ist.lowcoding.common.util.JWTUtil;
import cn.ist.lowcoding.common.util.ResultUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    private List<SkipMatcher> matchers = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.matchers.add(new SkipMatcher("POST", "/login"));
        this.matchers.add(new SkipMatcher("POST", "/register"));
        this.matchers.add(new SkipMatcher("PUT", "/as/*/instance/*"));
        this.matchers.add(new SkipMatcher("POST", "/file"));
        this.matchers.add(new SkipMatcher("GET", "/file/*"));
        /*
         *  swagger资源
         */
        this.matchers.add(new SkipMatcher("GET", "/swagger-ui.html"));
        this.matchers.add(new SkipMatcher("GET", "/swagger-ui.html/**"));
        this.matchers.add(new SkipMatcher("GET", "/webjars/**"));
        this.matchers.add(new SkipMatcher("GET", "/*/api-docs"));
        this.matchers.add(new SkipMatcher("GET", "/v2/**"));
        this.matchers.add(new SkipMatcher("GET", "/**/swagger-resources"));
        this.matchers.add(new SkipMatcher("GET", "/**/swagger-resources/configuration/ui"));
        this.matchers.add(new SkipMatcher("GET", "/**/swagger-resources/configuration/security"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String url = request.getRequestURI();
        if (!canSkip(method, url)) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                ResultUtil.failure(response, "缺失或无效的 Authorization 请求头");
                return false;
            }

            // 获取 token
            final String token = authHeader.substring(7);

            try {
                // 验证 token
                JWTUtil.verify(token);
                // 在 request 中设置相关属性
                request.setAttribute("userId", JWTUtil.decodeAudience(token));
                request.setAttribute("roles", JWTUtil.decodeRole(token));
                request.setAttribute("userFields", JWTUtil.decodeUserFields(token));
                request.setAttribute("token", token);
            } catch (final JWTVerificationException e) {
                ResultUtil.failure(response, "无效的 token");
                return false;
            }
        }
        return true;
    }

    private boolean canSkip(String method, String url) {
        for (SkipMatcher matcher: matchers) {
            if (matcher.match(method, url)) {
                return true;
            }
        }
        return false;
    }

    static class SkipMatcher {
        String method;
        String urlPattern;
        AntPathMatcher antPathMatcher;

        public SkipMatcher(String method, String urlPattern) {
            this.method = method;
            this.urlPattern = urlPattern;
            this.antPathMatcher = new AntPathMatcher();
        }

        public boolean match(String method, String url) {
            return this.method.equals(method)  && antPathMatcher.match(urlPattern, url);
        }
    }
}
