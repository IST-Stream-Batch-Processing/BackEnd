package com.example.streamservice;

//import com.example.streamservice.service.HotItemsService;
import com.example.streamservice.freemaker.FreeMakerDataModel;
import com.example.streamservice.util.CodeGenerate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class StreamServiceApplicationTests {

    @Test
    public void test() throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        FreeMakerDataModel freeMakerDataModel = new FreeMakerDataModel();
        freeMakerDataModel.generate();
        CodeGenerate codeGenerate = new CodeGenerate();

        String dataName = "DataClass.java";
        codeGenerate.javac(dataName);
        String pathName = "DataClass";
        Class<?> clazz = codeGenerate.java(pathName);
        Object object = clazz.newInstance();
        System.out.println(object);
    }


}
