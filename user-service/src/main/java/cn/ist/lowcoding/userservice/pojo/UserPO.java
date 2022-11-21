package cn.ist.lowcoding.userservice.pojo;

import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.userservice.model.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class UserPO {
    @Id
    String id;

    String username;

    String password;

    Set<Role> roles;

    public User to() {
        User res = new User();
        BeanUtils.copyProperties(this, res);
        return res;
    }

    public static UserPO from(User user) {
        UserPO res = new UserPO();
        BeanUtils.copyProperties(user, res);
        return res;
    }
}
