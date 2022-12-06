package cn.ist.lowcoding.streamservice.controller;

import cn.ist.lowcoding.common.util.ResultUtil;

import cn.ist.lowcoding.streamservice.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@RestController
public class RunController {
    @Autowired
    RunService runService;

    @GetMapping("/run/{combinationId}")
    public void generate(@PathVariable("combinationId") String combinationId){
        runService.generate(combinationId);
    }

    @GetMapping("/run/{combinationId}")
    public void compile(@PathVariable("combinationId") String combinationId) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        runService.compile(combinationId);
    }

}
