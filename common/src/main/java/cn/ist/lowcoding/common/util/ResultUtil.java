package cn.ist.lowcoding.common.util;


import cn.ist.lowcoding.common.response.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultUtil {
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMessage("success");
        result.setCode(0);
        return result;
    }

    public static Result<String> success() {
        Result<String> result = new Result<>();
        result.setMessage("success");
        result.setCode(0);
        return result;
    }

    public static <T> Result<T> failure(String message, int code) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static void failure(HttpServletResponse response, String reason) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JsonUtil.writeValueAsString(ResultUtil.failure(reason, -1)));
    }
}
