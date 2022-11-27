package ${classPath};

public class ${className?cap_first}_${userId} {

<#list dataList as data>
    private ${data.type} ${data.name};
</#list>

    public ${className?cap_first}(){
    }

    public ${className?cap_first}(<#list dataList as data>${data.type}  ${data.name}<#if data_has_next>,</#if></#list>){
    <#list dataList as data>
        this.${data.name}=${data.name};
    </#list>
    }

<#list dataList as data>

    public void set${data.name?cap_first} (${data.type}  ${data.name}) {
        this.${data.name}=${data.name};
    }

    public  ${data.type} get${data.name?cap_first}() {
        return this.${data.name};
    }
</#list>
}
