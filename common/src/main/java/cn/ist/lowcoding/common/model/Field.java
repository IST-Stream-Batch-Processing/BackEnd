package cn.ist.lowcoding.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    String key;

    String name;

    String alias;

    Type type;

    public Field(String name, Type type) {
        this.key = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
    }
}
