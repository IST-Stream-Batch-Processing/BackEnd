package com.example.streamservice.freemaker;


import com.example.streamservice.pojo.Data;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMakerDataModel {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/com/example/streamservice/pojo";
    private List<String> type = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private String className;//

    public void setType(List<String> type) {
        this.type = type;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public void generate(){
        //测试用
        type.add("Long");
        type.add("Long");
        type.add("Integer");
        type.add("String");
        type.add("Long");
        name.add("userId");
        name.add("itemId");
        name.add("categoryId");
        name.add("behavior");
        name.add("timestamp");

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<Data> dataList = new ArrayList<>();
            for(int i=0;i<type.size();i++){
                dataList.add(new Data(type.get(i),name.get(i)));
            }

            dataMap.put("classPath", "com.example.streamservice.pojo");
            dataMap.put("className", className);
            dataMap.put("dataList", dataList);

            // step4 加载模版文件
            Template template = configuration.getTemplate("dataModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "DataClass.java");
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
