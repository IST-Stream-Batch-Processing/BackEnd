package ${packagePath};
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.net.URL;

public class StreamCombination${combinationId} {
    public void run() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<${String}> dataStream0 = env.readTextFile("D:\\BackEnd\\stream-service\\src\\main\\resources\\file\\UserBehavior.csv");
        <#list streamList as stream>
        ${stream.output} dataStream${stream_index+1} = ${stream.name}${stream.id}.process(dataStream${stream_index});
        <#if stream_has_next>
            <#else>
            dataStream${stream_index+1}.print();
        </#if>
        </#list>
        env.execute();

    }
}
