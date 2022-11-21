package cn.ist.lowcoding.common.util;

import cn.ist.lowcoding.common.model.Field;
import cn.ist.lowcoding.common.model.Type;
import cn.ist.lowcoding.common.model.types.FileType;
import cn.ist.lowcoding.common.model.types.ListType;
import cn.ist.lowcoding.common.model.types.ObjectType;

public class TypeUtil {
    public static boolean containsFileField(Type type) {
        if (type instanceof FileType) {
            return true;
        }
        if (type instanceof ObjectType) {
            for (Field field: ((ObjectType) type).getFields()) {
                if (containsFileField(field.getType())) {
                    return true;
                }
            }
        }
        if (type instanceof ListType) {
            return containsFileField(((ListType) type).getMemberType());
        }
        return false;
    }
}
