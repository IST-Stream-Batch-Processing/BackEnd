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
    private static final String TEMPLATE_PATH = "./src/main/resources/templates";
    private static final String CLASS_PATH = "./src/main/java/cn/ist/lowcoding/streamservice/generateClass";
    private static final String PACKAGE_PATH = "cn.ist.lowcoding.streamservice.generateClass";

    private String operatorId;//算子标识
    private String originalType;//
    private String finalType;
    private String dataId;
    private Boolean isSpilt;//是否需要分割字符串
    private String delimiter;//分隔符
    private String timeStampType;//时间戳类型
    private String regexFormat;//时间戳格式
    private Integer timeStampIndex;//时间戳在分割后字符串列表终的位置
    private List<TypeAndIndex> dataList = new ArrayList<>();//数据属性类型与对应在数据流中的位置

    public void generate(){

//        String originalType = "String";
//        String finalType = "DataClass";
//        Boolean isSpilt = true;
//        String delimiter = ",";
//        String timeStampType = "String";
//        String regexFormat = "dd/MM/yyyy:HH:mm:ss";
//        Integer timeStampIndex = 4;
//        List<String> type = new ArrayList<>();
//        List<Integer> indexs = new ArrayList<>();
//        type.add("Long");
//        type.add("Long");
//        type.add("Integer");
//        type.add("String");
//        type.add("Long");
//        indexs.add(0);
//        indexs.add(1);
//        indexs.add(2);
//        indexs.add(3);
//        indexs.add(4);

        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("operatorId",operatorId);
            dataMap.put("packagePath",PACKAGE_PATH);
            dataMap.put("originalType",originalType);
            dataMap.put("finalType",finalType);
            dataMap.put("dataId",dataId);
            if(originalType.equals("String")){
                dataMap.put("isSpilt",isSpilt);
                if(isSpilt){
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
            File docFile = new File(CLASS_PATH + "\\" + "StreamMapConstruct"+operatorId+".java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("StreamMapConstruct"+operatorId+".java 文件创建成功 !");
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
