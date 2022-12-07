### 包说明

freemaker：从模板动态生成.java文件

generateClass：存放动态生成的.java文件

model：存放DO

​               /data：数据相关

​              /stream:算子相关，继承父类OperatorDO

### 数据注册：

data/DataDO：前端需要提供的数据，注意：提供指定时间戳功能，用户指定的时间戳属性类型为Long，并记录

​                           下间戳属性名称

FMDataModel（dataModel.ftl）：控制生成数据类

​      输入：String：className；String：userId；List<String>：type；List<String> name

DataController : /register

### 算子

#### MapConstruct

功能：将数据流转换成对应注册数据

MapConstructDO

FMMapConstruct（mapConstructModel.ftl）

界面展示：

```
finalType：dataSourceId对应的数据的className
originalType：固定为String
输入isSplit：是否需要分割数据流
 是：输入分割符delimiter
输入数据流中时间戳属性类型timeStampType
 判断是否为String
  是：输入数据流中时间戳属性格式regexFormat和在分割后字符串列表中的位置timeStampIndex（从0开始）
构造：展示时左侧为属性类型和属性名，右侧输入对应在数据流中的位置（从0开始）
```
![img.png](doc-images/img-mapConstruct.png)
![img.png](doc-images/img-ascendingTimestamp.png)
![img.png](doc-images/img-filterDataClassOne.png)
timeWindow算子keyBYDataClass可传空
![img.png](doc-images/img-keyByDataClass.png)
timeWindow算子finalType可传空
![img.png](doc-images/img-timeWindow.png)
![img.png](doc-images/img-windowViewCount.png)
![img.png](doc-images/img-processListState.png)

#### 操作顺序说明

1. 注册数据源

   ```
   {
       "filePath":"Userbehavior",
       "userId": "638fe162a355ca0449afc0e5",
       "combinationIds":[],
       "className":"UserBehavior",
       "attributeList":[
           {
               "type":"Long",
               "name":"itemId"
           },
           {
               "type":"Long",
               "name":"userId"
           },
           {
               "type":"Integer",
               "name":"categoryId"
           },
           {
               "type":"String",
               "name":"behavior"
           },
           {
               "type":"Long",
               "name":"timeStamp"
           }
       ],
       "isTimeStamp":true,
       "timeStampName":"timeStamp"
   }
   ```

   2.注册编排

   ```
   {
       "name":"firstCombination",
       "dataId": "6390407736e4e2450773b8cd",
       "operatorIds":[],
       "finalTypes":[] 
   }
   ```

   3.mapConstruct

   ```
   {
       "combinationId":"6390417536e4e2450773b8ce",
       "originalType":"String",
       "finalType":"UserBehavior",
       "isSplit":true,
       "delimiter":",",
       "timeStampType":"Long",
       "regexFormat":true,
       "timeStampIndex":0,
       "dataList":[
           {
               "type":"Long",
               "index":0
           },
           {
               "type":"Long",
               "index":1
           },
           {
               "type":"Integer",
               "index":2
           },
           {
               "type":"String",
               "index":3
           },
           {
               "type":"Long",
               "index":4
           }
       ]
   }
   ```

   4.ascendingTimeStamp

   ```
   {
       "combinationId":"6390417536e4e2450773b8ce",
       "originalType":"UserBehavior",
       "finalType":"UserBehavior",
       "timeStampName":"timeStamp",
       "unit":"s"
        
   }
   ```

   5.filterDataClassOne

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"UserBehavior",
    "finalType":"UserBehavior",
    "filterName":"behavior",
    "value":"pv",
    "isRegex":false,
    "regex":""
}
```

6.keyByDataClass

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"UserBehavior",
    "finalType":"UserBehavior",
    "keyType":"Long",
    "keyName":"itemId"
}
```

7.timeWindow

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"UserBehavior",
    "finalType":"UserBehavior",
    "isSlide":true,
    "lengthUnit":"hour",
    "length":"1",
    "intervalUnit":"minute",
    "interval":"5",
    "keyType":"Long",
    "keyName":"itemId" 
}
```

8.windowViewCount

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"",
    "finalType":"UserBehavior,Long,TimeWindow",
    "keyType":"Long",
    "keyName":"itemId",
    "attributeList":[
        {
            "type":"Long",
            "name":"itemId"
        },
        {
            "type":"Long",
            "name":"windowEnd"
        },
        {
            "type":"Long",
            "name":"count"
        }

    ]
}
```

9.Aggregate

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"UserBehavior,Long,TimeWindow",
    "finalType":"WindowViewCount",
    "keyType":"Long"
}
```

10.keyByDataClass

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"WindowViewCount",
    "finalType":"WindowViewCount",
    "keyType":"Long",
    "keyName":"windowEnd"
}
```

11.processListState

```
{
    "combinationId":"6390417536e4e2450773b8ce",
    "originalType":"WindowViewCount",
    "finalType":"String",
    "keyType":"Long",
    "isTop":true,
    "topSize":5,
    "isSort":true,
    "isDescending":true
}
```

