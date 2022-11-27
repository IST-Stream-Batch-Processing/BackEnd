package com.example.streamservice;

//import com.example.streamservice.service.HotItemsService;
import com.example.streamservice.freemaker.FMDataModel;
import com.example.streamservice.model.data.TypeAndName;
import com.example.streamservice.util.CodeGenerate;
import org.assertj.core.util.diff.Delta;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class StreamServiceApplicationTests {

    @Test
    public void test() throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        FreeMakerDataModel freeMakerDataModel = new FreeMakerDataModel();
//        freeMakerDataModel.generate();
//        CodeGenerate codeGenerate = new CodeGenerate();
//
//        String dataName = "DataClass.java";
//        codeGenerate.javac(dataName);
//        String pathName = "DataClass";
//        Class<?> clazz = codeGenerate.java(pathName);
//        Object object = clazz.newInstance();
//        System.out.println(object);
    }
    @Test
    public void testFMDataModel(){
        FMDataModel fmDataModel = new FMDataModel();
        fmDataModel.setClassName("Test");
        fmDataModel.setDataId("01");
        List<TypeAndName> attributeList = new ArrayList<>();
        TypeAndName typeAndName = new TypeAndName("String","itemId");
        attributeList.add(typeAndName);
        fmDataModel.setAttributes(attributeList);
        fmDataModel.generate();
    }


}
