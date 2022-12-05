package ${packagePath};
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class StreamTimeWindow${operatorId} {
    public static WindowedStream<${originalType?cap_first}, TimeWindow> process(KeyedStream<${originalType?cap_first}> inputsteam){
        WindowedStream<${originalType?cap_first}, TimeWindow> windowedStream = inputsteam
            <#if isSlide>
                .timeWindow(Time.${lengthUnit}s(${length}),Time.${intervalUnit}s(${interval}));
            <#else>
                .timeWindow(Time.${lengthUnit}s(${length}));
            </#if>
        return windowedStream;
    }
}