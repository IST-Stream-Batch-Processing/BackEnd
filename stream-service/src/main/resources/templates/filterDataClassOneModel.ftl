package ${packagePath};
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import java.util.regex.Pattern;

public class StreamFilterDataClassOne${operatorId} {
    public static DataStream<${originalType?cap_first}> process(DataStream<${originalType?cap_first}> inputStream){
        DataStream<${originalType?cap_first}> filterStream = inputStream
            .filter(new FilterFunction<${originalType?cap_first}>() {
                @Override
                public boolean filter(${originalType?cap_first} variable) throws Exception {
<#--                    <#if type == "String">-->
                    <#if isRegex>
                        String regex = "${regex}";
                        return Pattern.matches(regex, variable.get${name?cap_first}());
                    <#else>
                    return variable.get${name?cap_first}().equals("${value}");
                    </#if>
<#--                    <#elseif type == "Long">-->
<#--                    return variable.get${name?cap_first}() == ${value};-->
<#--                    </#if>-->
                }
            });
        return filterStream;
    }
}