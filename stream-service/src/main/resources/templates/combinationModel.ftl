package ${packagePath};
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class StreamCombination${combinationId} {
    public static void run() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        URL resource = StreamCombination.class.getResource("/UserBehavior.csv");
        DataStream<${String}> dataStream0 = env.readTextFile(resource.getPath());
        <#list streamList as stream>
        ${stream.output} dataStream${stream_index+1} = ${stream.name}${stream.id}.process(dataStream${stream_index});
        <#if stream_has_next>
            <#else>
            dataStream${stream_index+1}.print();
        </#if>
        </#list>
        env.execute();
