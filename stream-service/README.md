### 包说明

freemaker：从模板动态生成.java文件

generateClass：存放动态生成的.java文件

model：存放DO

​               /data：数据相关

​              /stream:算子相关

### 数据注册：

data/DataDO：前端需要提供的数据

FMDataModel（dataModel.ftl）：控制生成数据类

​      输入：String：className；String：userId；List<String>：type；List<String> name

DataController : /register