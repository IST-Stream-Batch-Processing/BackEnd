package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


//筛选出注册数据中某一个属性为固定值的数据流
@Data
public class FMFilterDataClassOne {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String operatorId;//算子标识
    private String originalType;//输入数据流类型
    private String type;//筛选目标属性类型
    private String name;//筛选目标属性名称
    private String value;//筛选目标值
    private Boolean isRegex;//是否需要正则表达
    private String regex;//正则表达式

    public void generate() {
//        String originalType = "DataClass";
//        String type = "Long";
//        String name = "timestamp";
//        String value = "123456";
//        Boolean isRegex = true;
//        String regex ="123456";

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("filterDataClassOneModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamFilerDataClassOne"+operatorId+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("operatorId",operatorId);
            dataMap.put("type",type);
            dataMap.put("name",name);
            dataMap.put("value",value);
            dataMap.put("isRegex",isRegex);
            if(isRegex){
                dataMap.put("regex",regex);
            }

            template.process(dataMap, out);
            System.out.println("StreamFilerDataClassOne"+operatorId+".java 文件创建成功 !");
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
