package ${packagePath};
import cn.ist.lowcoding.streamservice.generateClass.*;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class StreamAggregate${operatorId}   {

    public static DataStream<${finalType?cap_first}> process(WindowedStream<${originalType?cap_first}, ${keyType?cap_first}, TimeWindow> inputsteam) {
        DataStream<${finalType?cap_first}> dataStream = inputsteam
            .aggregate(new CountAgg(), new CountResult());
        return dataStream;
    }
    public static class CountAgg implements AggregateFunction<${originalType?cap_first}, Long, Long> {
        @Override
        public Long createAccumulator() {
            return 0L;
        }

        @Override
        public Long add(${originalType?cap_first} variable, Long accumulator) {
            return accumulator + 1;
        }

        @Override
        public Long getResult(Long accumulator) {
            return accumulator;
        }

        @Override
        public Long merge(Long a, Long b) {
            return a + b;
        }
    }
    public static class CountResult implements WindowFunction<${keyType?cap_first}, ${finalType?cap_first}, Long, TimeWindow> {
        @Override
        public void apply(${keyType?cap_first} key, TimeWindow timeWindow, Iterable<${LongType}> iterable, Collector<${finalType?cap_first}> collector) throws Exception {
                ${keyType?cap_first} keyContent = key;
                Long windowEnd = timeWindow.getEnd();
                Long count = iterable.iterator().next();
                collector.collect(new ${finalType?cap_first}(keyContent,windowEnd,count));
        }
    }
}