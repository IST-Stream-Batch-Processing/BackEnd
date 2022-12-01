package ${packagePath};
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;

public class StreamKeyByDataClass${operatorId}{
    public static KeyedStream<${originalType?cap_first}, ${keyType?cap_first}> process(DataStream<${originalType?cap_first}> dataStream){
        KeyedStream<${originalType?cap_first}, ${keyType?cap_first}> keyedStream = dataStream
            .keyBy(new KeySelector<${originalType?cap_first}, ${keyType?cap_first}>() {
                @Override
                public ${keyType?cap_first} getKey(${originalType?cap_first} variable) throws Exception {
                    return variable.get${keyName?cap_first}();
                }
        });
        return keyedStream;
    }
}