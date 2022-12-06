package cn.ist.lowcoding.streamservice.freemaker;

import cn.ist.lowcoding.streamservice.model.data.TypeAndIndex;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class FMMapConstruct {
    private static final String TEMPLATE_PATH = "./stream-service/src/main/resources/templates";
    private static final String CLASS_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String id;//算子标识
    private String originalType;//
    private String finalType;
    private Boolean isSplit;//是否需要分割字符串
    private String delimiter;//分隔符
    private String timeStampType;//时间戳类型
    private String regexFormat;//时间戳格式
    private Integer timeStampIndex;//时间戳在分割后字符串列表终的位置
    private List<TypeAndIndex> dataList = new ArrayList<>();//数据属性类型与对应在数据流中的位置

    public void generate(){

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("operatorId",id);
            dataMap.put("packagePath",PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("finalType",finalType);
            if(originalType.equals("String")){
                dataMap.put("isSplit",isSplit);
                if(isSplit){
                    dataMap.put("delimiter",delimiter);
                }
            }
            dataMap.put("timeStampType",timeStampType);
            if(timeStampType.equals("String")){
                dataMap.put("regexFormat",regexFormat);
                dataMap.put("timeStampIndex",timeStampIndex);
            }

            dataMap.put("dataList", dataList);

            // step4 加载模版文件
            Template template = configuration.getTemplate("mapConstructModel.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "StreamMapConstruct"+id+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("StreamMapConstruct"+id+".java 文件创建成功 !");
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
