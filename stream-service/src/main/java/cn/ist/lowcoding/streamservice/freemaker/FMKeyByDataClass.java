package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
//key为注册数据的属性分组
public class FMKeyByDataClass {
    private static final String TEMPLATE_PATH = "./stream-service/src/main/resources/templates";
    private static final String CLASS_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;//算子标识
    private String originalType;//输入数据流类型
    private String keyName;//key对应的属性名称
    private String keyType;//key对应的属性类型


    public void generate() {

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("keyByDataClassModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamKeyByDataClass"+id+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("operatorId",id);
            dataMap.put("keyType",keyType);
            dataMap.put("keyName",keyName);
            template.process(dataMap, out);
            System.out.println("StreamKeyByDataClass"+id+".java 文件创建成功 !");
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
