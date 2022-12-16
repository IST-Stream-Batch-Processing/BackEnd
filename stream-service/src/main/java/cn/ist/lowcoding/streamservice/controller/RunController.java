package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.response.Result;
import cn.ist.lowcoding.common.util.ResultUtil;

import cn.ist.lowcoding.streamservice.service.RunService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * RunController现已整合至CombinationController中，将在未来版本中删除。
 */
@Deprecated
@RestController
public class RunController {
    @Autowired
    RunService runService;

    @Deprecated
    @ApiOperation(value = "根据模板生成.java文件", notes = "建议改为调用CombinationController中的generateCombinationById方法")
    @GetMapping("/run/generate/{combinationId}")
    public Result<String> generate(@PathVariable("combinationId") String combinationId){
        runService.generate(combinationId);
        return ResultUtil.success();
    }

    @Deprecated
    @ApiOperation(value = "将.java文件编译成.class文件并运行", notes = "建议改为调用CombinationController中的runCombinationById方法")
    @GetMapping("/run/{combinationId}")
    public Result<String> run(@PathVariable("combinationId") String combinationId) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        runService.compile(combinationId);
        return ResultUtil.success();
    }

}
