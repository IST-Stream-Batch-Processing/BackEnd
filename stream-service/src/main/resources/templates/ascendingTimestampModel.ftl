package ${packagePath};
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;

public class StreamAscendingTimeStamp${operatorId} {

    public static DataStream<${originalType?cap_first}> process(DataStream<${originalType?cap_first}> inputStream){
        DataStream<${originalType?cap_first}> dataStream = inputStream
            .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<${originalType?cap_first}>() {
                @Override
                public long extractAscendingTimestamp(${originalType?cap_first} variable) {
                    <#if unit == "s">
                    return variable.get${timeStampName?cap_first}() * 1000L;
                    <#else>
                    return variable.get${timeStampName?cap_first}();
                    </#if>
                }
            });
        return dataStream;
    }
}
