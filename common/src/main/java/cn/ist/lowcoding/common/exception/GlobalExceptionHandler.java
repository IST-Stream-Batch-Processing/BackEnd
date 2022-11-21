package cn.ist.lowcoding.common.exception;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseBody
    ResponseEntity handle(DependencyException e) {
        log.error("", e);
        Result res = new Result();
        res.setData(e.getDependencies());
        res.setCode(-2);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @ExceptionHandler()
    @ResponseBody
    ResponseEntity handle(RuntimeException e) {
        log.error("", e);
        Result result = ResultUtil.failure(e.getMessage(), -1);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
