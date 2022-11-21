package cn.ist.lowcoding.userservice.pojo.dto.response;

import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    String id;

    Set<Role> roles;

    Map<String, String> userFields = new HashMap<>();

    public static UserVO from(User user) {
        UserVO res = new UserVO();
        BeanUtils.copyProperties(user, res);
        res.userFields.put("username", user.getUsername());
        return res;
    }
}
