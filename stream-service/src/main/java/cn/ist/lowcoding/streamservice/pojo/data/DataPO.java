package cn.ist.lowcoding.streamservice.pojo.data;

import cn.ist.lowcoding.streamservice.model.data.Data;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.List;

@lombok.Data
public class DataPO {
    @Id
    private String id;

    private String userId;

    private String dataId;

    private String className;

    private List<TypeAndName> attributeList;

    private Boolean isTimeStamp;

    private String timeStampName;

    public Data to() {
        Data res = new Data();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static DataPO from(Data data) {
        DataPO res = new DataPO();
        BeanUtils.copyProperties(data, res);
        return res;
    }
}
