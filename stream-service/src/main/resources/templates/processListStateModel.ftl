package ${packagePath};
import org.apache.commons.compress.utils.Lists;
import org.apache.flink.api.common.state.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import cn.ist.lowcoding.streamservice.util.WebSocketServer;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;


public class StreamProcessListState${operatorId} {

    public static DataStream<${finalType?cap_first}> process(KeyedStream<${originalType?cap_first},${keyType?cap_first}> inputsteam,String sessionId) {
        DataStream<${finalType?cap_first}> dataStream = inputsteam
            .process(new FinalResult(<#if isTop>${topSize},</#if>sessionId));
        return dataStream;
    }

    public static class FinalResult extends KeyedProcessFunction<${keyType?cap_first},${originalType?cap_first},${finalType?cap_first}> {

        private Integer topSize;
        private String sessionId;
        public FinalResult(String sessionId){ this.sessionId = sessionId;}

        public FinalResult(Integer topSize,String sessionId) {
            this.topSize = topSize;
            this.sessionId = sessionId;
        }

        ListState<${originalType?cap_first}> itemViewCountListState;

        @Override
        public void open(Configuration parameters) throws Exception {
            itemViewCountListState = getRuntimeContext().getListState(new ListStateDescriptor<${originalType?cap_first}>("item-view-count-list",${originalType?cap_first}.class));
        }

        @Override
        public void processElement(${originalType?cap_first} itemViewCount, Context context, Collector<${finalType?cap_first}> collector) throws Exception {
            itemViewCountListState.add(itemViewCount);
            context.timerService().registerEventTimeTimer(itemViewCount.getWindowEnd()+1);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<${finalType?cap_first}> out) throws Exception {
            ArrayList<${originalType?cap_first}> itemViewCounts = Lists.newArrayList(itemViewCountListState.get().iterator());
            <#if isSort>
            itemViewCounts.sort(new Comparator<${originalType?cap_first}>() {
                @Override
                public int compare(${originalType?cap_first} o1, ${originalType?cap_first} o2) {
                    <#if isDescending>
                    return o2.getCount().intValue() - o1.getCount().intValue();
                    <#else>
                    return o1.getCount().intValue() - o2.getCount().intValue();
                    </#if>
                }
            });
            </#if>

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("===============================");
            resultBuilder.append("窗口结束时间：").append(new Timestamp(timestamp - 1)).append("\n");

            //遍历列表，取Top n输出
           <#if isTop>
            for(int i=0;i<${Math}.${min}(topSize,itemViewCounts.size());i++){
           <#else>
               for(int i=0;i<${itemViewCounts}.size();i++){
           </#if>
                ${originalType?cap_first} currentItemViewCount = itemViewCounts.get(i);
                resultBuilder.append("No ").append(i+1).append(":")
                .append("itemId: ").append(currentItemViewCount.getItemId())
                .append("count: ").append(currentItemViewCount.getCount())
                .append("\n");
            }
            resultBuilder.append("=============================================\n\n");
            out.collect(resultBuilder.toString());
            WebSocketServer websocketServer = WebSocketServer.getWebSocketServerBySessionId(sessionId);
            websocketServer.sendMessage(resultBuilder.toString());

        }
    }
}