package cn.ist.lowcoding.streamservice.freemaker;

import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
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
    private static final String TEMPLATE_PATH = "./stream-service/src/main/resources/templates";
    private static final String CLASS_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private List<TypeAndName> attributeList = new ArrayList<>();
    private String className;//注册的数据名
    private String id ="";//注册数据的id

    public void generate(){
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();

            dataMap.put("classPath", PACKAGE_PATH);
            dataMap.put("className", className);
            dataMap.put("dataId", id);
            dataMap.put("dataList", attributeList);

            // step4 加载模版文件
            Template template = configuration.getTemplate("dataModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + className+ id + ".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println(className+ id + ".java 文件创建成功 !");
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
