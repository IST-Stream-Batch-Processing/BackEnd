package cn.ist.lowcoding.streamservice;

import cn.ist.lowcoding.streamservice.freemaker.*;
import cn.ist.lowcoding.streamservice.generateClass.StreamCombination638da9ebfece611588ed4e4d;

import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import cn.ist.lowcoding.streamservice.repository.DataRepo;
import cn.ist.lowcoding.streamservice.util.CodeGenerate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class StreamServiceApplicationTests {
//    @Autowired
//    DataRepo dataRepo;

    @Test
    public void test() throws Exception {
        StreamCombination638da9ebfece611588ed4e4d.run();
    }

    @Test
    public void testFMDataModel(){
        FMDataModel fmDataModel = new FMDataModel();
//        Data data = dataRepo.findById("638da9c8fece611588ed4e4c").orElseThrow(() -> new RuntimeException(""));
        CodeGenerate codeGenerate = new CodeGenerate();
//        fmDataModel.setId("");
//        codeGenerate.javac(data);
//        BeanUtils.copyProperties(data, fmDataModel);
        fmDataModel.setClassName("UserBehavior");
        fmDataModel.setId("01");
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
        fmDataModel.setAttributeList(attributeList);
        fmDataModel.generate();
        codeGenerate.javac("UserBehavior01.java");
    }

    @Test
    public void testFMMapConstruct(){
        FMMapConstruct fmMapConstruct = new FMMapConstruct();
        fmMapConstruct.setId("01");
        fmMapConstruct.setFinalType("UserBehavior");
//        fmMapConstruct.setDataId("01");
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

    @Test
    public void testAscendingTimeStamp(){
        FMAscendingTimeStamp fmAscendingTimeStamp = new FMAscendingTimeStamp();
        fmAscendingTimeStamp.setUnit("s");
        fmAscendingTimeStamp.setTimeStampName("timeStamp");
        fmAscendingTimeStamp.setId("01");
        fmAscendingTimeStamp.setOriginalType("UserBehavior01");
        fmAscendingTimeStamp.generate();
    }

    @Test
    public void testFilterDataClassOne(){
        FMFilterDataClassOne fmFilterDataClassOne = new FMFilterDataClassOne();
        fmFilterDataClassOne.setId("01");
        fmFilterDataClassOne.setOriginalType("UserBehavior");
        fmFilterDataClassOne.setFilterName("behavior");
//        fmFilterDataClassOne.setFilterType("String");
        fmFilterDataClassOne.setIsRegex(false);
        fmFilterDataClassOne.setValue("pv");
        fmFilterDataClassOne.generate();
    }

    @Test
    public void testKeyBYDataClass(){
        FMKeyByDataClass fmKeyByDataClass = new FMKeyByDataClass();
        fmKeyByDataClass.setId("01");
        fmKeyByDataClass.setKeyName("itemId");
        fmKeyByDataClass.setKeyType("Long");
        fmKeyByDataClass.setOriginalType("UserBehavior");
        fmKeyByDataClass.generate();
    }

    @Test
    public void testTimeWindow(){
//       FMTimeWindow fmTimeWindow = new FMTimeWindow();
//       fmTimeWindow.setIsSlide(true);
//       fmTimeWindow.setOperatorId("01");
//       fmTimeWindow.setLength("1");
//       fmTimeWindow.setInterval("5");
//       fmTimeWindow.setLengthUnit("hour");
//       fmTimeWindow.setIntervalUnit("minute");
//       fmTimeWindow.setOriginalType("UserBehavior");
//       fmTimeWindow.generate();
    }

    @Test
    public void testWindowViewCount(){

    }

    @Test
    public void testAggregate(){

    }
}
