package ${packagePath};
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class StreamMapConstruct${operatorId} {

public static DataStream<${finalType?cap_first}> process(DataStream<${originalType?cap_first}> inputStream){
        DataStream<${finalType?cap_first}> dataStream = inputStream
            .map(new MapFunction<${originalType?cap_first}, ${finalType?cap_first}>() {
                @Override
                public ${finalType?cap_first} map(${originalType?cap_first} variable) throws Exception {
                    <#if isSplit>
                    String[] fields = variable.split("${delimiter}");
                    </#if>
                    <#if timeStampType == "String">
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("${regexFormat}");
                    Long timestamp = simpleDateFormat.parse(fields[${timeStampIndex}]).getTime();
                    return new ${finalType?cap_first}(<#list dataList as data><#if data_index == timeStampIndex>timestamp<#elseif data.type != "String">new ${data.type}(fields[${data.index}])<#else>fields[${data.index}]</#if><#if data_has_next>, </#if></#list>);
                    <#else>
                        return new ${finalType?cap_first}(<#list dataList as data><#if data.type != "String">new ${data.type}(fields[${data.index}])<#else>fields[${data.index}]</#if><#if data_has_next>, </#if></#list>);
                    </#if>
                }
            });
            return dataStream;
        }
}
