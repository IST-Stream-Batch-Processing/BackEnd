package com.example.streamservice.freemaker;

//数据类中的一个属性：类型和名称
public class TypeAndName {
    private String type;
    private String name;

    public TypeAndName() {
    }

    public TypeAndName(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
