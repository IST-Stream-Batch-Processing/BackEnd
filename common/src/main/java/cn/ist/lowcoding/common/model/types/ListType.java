package cn.ist.lowcoding.common.model.types;

import cn.ist.lowcoding.common.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListType extends Type {
    Type memberType;

    public static String getMemberTypeName(String listName) {
        int preIndex = 0;
        int sufIndex = listName.length() - 1;
        while (preIndex < listName.length() && listName.charAt(preIndex) != '<')
            preIndex++;
        while (sufIndex >= 0 && listName.charAt(sufIndex) != '>')
            sufIndex--;
        if (preIndex + 1 >= sufIndex) {
            throw new RuntimeException("你这List类型有问题啊");
        }
        return listName.substring(preIndex + 1, sufIndex);
    }
}
