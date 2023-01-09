package ${packagePath};
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import java.util.Random;

public class StreamMapAndKeyByRandom${operatorId} {
    public static KeyedStream<${tuple2}<${integer}, Long>,${integer}> process(DataStream<${originalType?cap_first}> inputStream) {
            KeyedStream<${tuple2}<${integer}, Long>,${integer}> dataStream =inputStream
            .map(new MapFunction<${originalType?cap_first}, Tuple2<${integer}, Long>>() {
                @Override
                public Tuple2<${integer}, Long> map(${originalType?cap_first} value) throws Exception {
                    Random random = new Random();
                    return new Tuple2<>(random.nextInt(${randomSize}), 1L);
                }
            })
            .keyBy(data -> data.f0);
        return dataStream;
    }
}