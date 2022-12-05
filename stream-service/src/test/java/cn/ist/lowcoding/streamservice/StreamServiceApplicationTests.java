package cn.ist.lowcoding.streamservice;

//import com.example.streamservice.service.HotItemsService;

import cn.ist.lowcoding.streamservice.freemaker.FMDataModel;
import cn.ist.lowcoding.streamservice.freemaker.FMMapConstruct;
import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
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
        fmDataModel.setClassName("UserBehavior");
        fmDataModel.setDataId("01");
        List<TypeAndName> attributeList = new ArrayList<>();
        TypeAndName typeAndName = new TypeAndName("Long","userId");
        attributeList.add(typeAndName);
        TypeAndName typeAndName1 = new TypeAndName("Long","itemId");
        attributeList.add(typeAndName1);
        TypeAndName typeAndName2 = new TypeAndName("Integer","categoryId");
        attributeList.add(typeAndName2);
        TypeAndName typeAndName3 = new TypeAndName("String","behavior");
        attributeList.add(typeAndName3);
        TypeAndName typeAndName4 = new TypeAndName("Long","timeStamp");
        attributeList.add(typeAndName4);
        fmDataModel.setAttributes(attributeList);
        fmDataModel.generate();
    }

    @Test
    public void testFMMapConstruct(){
        FMMapConstruct fmMapConstruct = new FMMapConstruct();
        fmMapConstruct.setOperatorId("01");
        fmMapConstruct.setFinalType("UserBehavior");
        fmMapConstruct.setDataId("01");
        fmMapConstruct.setOriginalType("String");
        fmMapConstruct.setIsSplit(true);
        fmMapConstruct.setDelimiter(",");
        fmMapConstruct.setTimeStampType("Long");
        List<TypeAndIndex> typeAndNameList = new ArrayList<>();
        TypeAndIndex typeAndIndex = new TypeAndIndex("Long",0);
        typeAndNameList.add(typeAndIndex);
        TypeAndIndex typeAndIndex1 = new TypeAndIndex("Long",1);
        typeAndNameList.add(typeAndIndex1);
        TypeAndIndex typeAndIndex2 = new TypeAndIndex("Integer",2);
        typeAndNameList.add(typeAndIndex2);
        TypeAndIndex typeAndIndex3 = new TypeAndIndex("String",3);
        typeAndNameList.add(typeAndIndex3);
        TypeAndIndex typeAndIndex4 = new TypeAndIndex("Long",4);
        typeAndNameList.add(typeAndIndex4);
        fmMapConstruct.setDataList(typeAndNameList);
        fmMapConstruct.generate();
    }




}
