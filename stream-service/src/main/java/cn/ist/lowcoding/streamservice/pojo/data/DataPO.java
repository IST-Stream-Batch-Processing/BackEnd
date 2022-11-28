package cn.ist.lowcoding.streamservice.pojo.data;

import cn.ist.lowcoding.streamservice.model.data.DataDO;
import cn.ist.lowcoding.streamservice.model.data.TypeAndName;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class DataPO {
    @Id
    private String dataSourceId;

    private String userId;

    private String dataId;

    private String className;

    private List<TypeAndName> attributeList;

    private Boolean isTimeStamp;

    private String timeStampName;

    public DataDO to() {
        DataDO res = new DataDO();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static DataPO from(DataDO data) {
        DataPO res = new DataPO();
        BeanUtils.copyProperties(data, res);
        return res;
    }
}
