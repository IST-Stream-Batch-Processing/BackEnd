package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class FMProcessValueState {
    private static final String TEMPLATE_PATH = "./stream-service/src/main/resources/templates";
    private static final String CLASS_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;//算子标识
    private String originalType;
    private String finalType;
    private String keyType;
    public void generate() {

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("processValueStateModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamProcessValueState"+ id +".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("finalType",finalType);
            dataMap.put("keyType",keyType);
            dataMap.put("operatorId", id);
            template.process(dataMap, out);
            System.out.println("StreamProcessValueState"+ id +".java 文件创建成功 !");
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
