package cn.ist.lowcoding.streamservice.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//有序数据流：提出时间戳和水位线
@Data
public class FMAscendingTimestamp {
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String operatorId;//算子标识
    private String originalType;//输入数据流类型
    private String typeStampName;//时间戳名
    private String unit;//时间戳单位

    public void generate (){

//        String originalType = "DataClass";
//        String timeStampName = "timestamp";
//        String unit = "ms";

        Configuration configuration = new Configuration();
        // step3 创建数据模型
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));

            Template template = configuration.getTemplate("ascendingTimestampModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamAscendingTimestamp"+operatorId+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));

            dataMap.put("packagePath", PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("timeStampName",typeStampName);
            dataMap.put("operatorId",operatorId);
            dataMap.put("unit",unit);

            template.process(dataMap, out);
            System.out.println("StreamAscendingTimestamp"+operatorId+".java 文件创建成功 !");
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
