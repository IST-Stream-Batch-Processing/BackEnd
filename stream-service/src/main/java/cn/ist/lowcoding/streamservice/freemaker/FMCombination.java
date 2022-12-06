package cn.ist.lowcoding.streamservice.freemaker;

import cn.ist.lowcoding.streamservice.model.stream.Operator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class FMCombination {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;
    private List<Operator> streamList;

    public void generate() {

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("combinationModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamCombination"+id+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("combinationId",id);
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("String", "String");
            dataMap.put("streamList",streamList);
//            dataMap.put("finalType",finalType);
//            dataMap.put("keyType",keyType);
//            dataMap.put("LongType",LongType);
            template.process(dataMap, out);
            System.out.println("StreamCombination"+id+".java 文件创建成功 !");
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
