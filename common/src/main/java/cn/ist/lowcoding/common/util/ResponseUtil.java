package cn.ist.lowcoding.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ResponseUtil {
    public static void fullFillResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8' '" + URLEncoder.encode(fileName, "UTF-8"));
    }
}
