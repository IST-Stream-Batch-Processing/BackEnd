package cn.ist.lowcoding.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpUtil {
    public static HttpHeaders generateFormHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(token));
        headers.setContentType(MediaType.parseMediaType("multipart/form-data; charset=UTF-8"));
        return headers;
    }

    public static HttpHeaders generateJsonHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer ".concat(token));
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        return headers;
    }
}
