package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class FMTimeWindow {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;
    private String originalType;//输入数据流类型（keyBy后）
//    private String keyType;//key对应的属性类型
    private Boolean isSlide;//是否为滑动窗口
    private String lengthUnit;//窗口长度单位：day，hour，mintue，second，millisecond
    private String length;//窗口长度
    private String intervalUnit;//窗洞窗口间隔单位
    private String interval;//间隔长度

    public void generate() {

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("timeWindowModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamTimeWindow"+id+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("operatorId",id);
            dataMap.put("originalType",originalType);
//            dataMap.put("keyType",keyType);
            dataMap.put("isSlide",isSlide);
            if(isSlide){
                dataMap.put("intervalUnit",intervalUnit);
                dataMap.put("interval",interval);
            }
            dataMap.put("lengthUnit",lengthUnit);
            dataMap.put("length",length);

            template.process(dataMap, out);
            System.out.println("StreamTimeWindow"+id+".java 文件创建成功 !");
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
