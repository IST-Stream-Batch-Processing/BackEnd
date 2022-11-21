package cn.ist.lowcoding.common.model;

import cn.hutool.core.util.ClassUtil;
import cn.ist.lowcoding.common.model.types.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.lang.reflect.ParameterizedType;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "name")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BoolType.class, name = "Bool"),
        @JsonSubTypes.Type(value = CharType.class, name = "Char"),
        @JsonSubTypes.Type(value = DoubleType.class, name = "Double"),
        @JsonSubTypes.Type(value = IntegerType.class, name = "Integer"),
        @JsonSubTypes.Type(value = ListType.class, name = "List"),
        @JsonSubTypes.Type(value = LongType.class, name = "Long"),
        @JsonSubTypes.Type(value = StringType.class, name = "String"),
        @JsonSubTypes.Type(value = ObjectType.class, name = "Object"),
        @JsonSubTypes.Type(value = FileType.class, name = "File"),
})

public abstract class Type {
    public static Type from(java.lang.reflect.Type type) {
        if (type instanceof ParameterizedType) {
            return new ListType(from(((ParameterizedType) type).getActualTypeArguments()[0]));
        }
        Class<?> clazz = (Class<?>) type;
        if (ClassUtil.isAssignable(char.class, clazz)) {
            return new CharType();
        }
        if (ClassUtil.isAssignable(Boolean.class, clazz)) {
            return new BoolType();
        }
        if (ClassUtil.isAssignable(Double.class, clazz)) {
            return new DoubleType();
        }
        if (ClassUtil.isAssignable(Integer.class, clazz)) {
            return new IntegerType();
        }
        if (ClassUtil.isAssignable(Long.class, clazz)) {
            return new LongType();
        }
        if (ClassUtil.isAssignable(String.class, clazz)) {
            return new StringType();
        }
        if (ClassUtil.isAssignable(File.class, clazz)) {
            return new FileType();
        }
        ObjectType obj = new ObjectType();
        obj.setClassName(clazz.getSimpleName());
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field f : fields) {
            obj.getFields().add(new Field(f.getName(), from(f.getGenericType())));
        }
        return obj;
    }
}
