package ${packagePath};
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import cn.ist.lowcoding.streamservice.util.WebSocketServer;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.Timestamp;

public class StreamProcessValueState${operatorId} {
    public static DataStream<${finalType?cap_first}> process(KeyedStream<${originalType?cap_first},Long> inputStream,String sessionId){
        DataStream<${finalType?cap_first}> dataStream = inputStream
            .process(new FinalResult(sessionId));
        return  dataStream;
    }

    public static class FinalResult extends KeyedProcessFunction<${keyType?cap_first}, ${originalType?cap_first}, ${finalType?cap_first}> {
        ValueState<${keyType?cap_first}> totalCountState;
        public FinalResult(){}
        private String sessionId;
        public FinalResult(String sessionId){ this.sessionId = sessionId;}
        @Override
        public void processElement(${originalType?cap_first} itemViewCount, Context context, Collector<${finalType?cap_first}> collector) throws Exception {
            totalCountState.update( totalCountState.value() + itemViewCount.getCount() );
            context.timerService().registerEventTimeTimer(itemViewCount.getWindowEnd() + 1);
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            totalCountState = getRuntimeContext().getState(new ValueStateDescriptor<${keyType?cap_first}>("total-count", Long.class, 0L));
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<${finalType?cap_first}> out) throws Exception {
            Long totalCount = totalCountState.value();
            Timestamp time = new Timestamp(timestamp - 1);
            out.collect(new ${finalType?cap_first}(1,ctx.getCurrentKey(), totalCount));
            WebSocketServer websocketServer = WebSocketServer.getWebSocketServerBySessionId(sessionId);
            websocketServer.sendMessage("time:"+time+"  "+"totalCount:"+ totalCount);
            totalCountState.clear();
        }

        }

}