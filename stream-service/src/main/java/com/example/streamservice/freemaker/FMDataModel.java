package com.example.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//注册数据
@Data
public class FMDataModel {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/com/example/streamservice/generateClass";
    private static final String PACKAGE_PATH = "com.example.streamservice.generateClass";
    //需要的
    private List<String> type = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private String className;
    private String userId;

    public void generate(){
//        type.add("Long");
//        type.add("Long");
//        type.add("Integer");
//        type.add("String");
//        type.add("Long");
//        name.add("userId");
//        name.add("itemId");
//        name.add("categoryId");
//        name.add("behavior");
//        name.add("timestamp");

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<TypeAndName> dataList = new ArrayList<>();
            for(int i=0;i<type.size();i++){
                dataList.add(new TypeAndName(type.get(i),name.get(i)));
            }

            dataMap.put("classPath", PACKAGE_PATH);
            dataMap.put("className", className);
            dataMap.put("userId", userId);
            dataMap.put("dataList", dataList);

            // step4 加载模版文件
            Template template = configuration.getTemplate("dataModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + className + ".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("DataClass.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
