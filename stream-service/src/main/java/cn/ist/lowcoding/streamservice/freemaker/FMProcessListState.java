package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class FMProcessListState {
    private static final String TEMPLATE_PATH = "./stream-service/src/main/resources/templates";
    private static final String CLASS_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;//算子标识
    private String originalType;
    private String keyType = "Long";//固定,以窗口结束时间为key
    private String Math = "Math";//固定
    private String min = "min";//固定
    private String itemViewCounts = "itemViewCounts";
    private String finalType;
    private Boolean isTop;//是否全部输出前N
    private int topSize;
    private Boolean isSort;//是否排序
    private Boolean isDescending;//是否从大到小排

    public void generate() {

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("processListStateModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamProcessListState"+ id +".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("operatorId", id);
            dataMap.put("finalType",finalType);
            dataMap.put("keyType",keyType);
            dataMap.put("itemViewCounts",itemViewCounts);
            dataMap.put("isTop",isTop);
            if(isTop){
                dataMap.put("topSize",topSize);
            }
            dataMap.put("isSort",isSort);
            if(isSort){
                dataMap.put("isDescending",isDescending);
            }
            dataMap.put("Math",Math);
            dataMap.put("min",min);
            template.process(dataMap, out);
            System.out.println("StreamProcessListState"+ id +".java 文件创建成功 !");
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
