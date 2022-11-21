package cn.ist.lowcoding.common.model.types;

import cn.ist.lowcoding.common.model.Field;
import cn.ist.lowcoding.common.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectType extends Type {
    String className;

    List<Field> fields = new ArrayList<>();
}
