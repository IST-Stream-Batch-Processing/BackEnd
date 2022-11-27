package com.example.streamservice.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//数据类中的一个属性：类型和名称
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAndName {
    private String type;
    private String name;
}
