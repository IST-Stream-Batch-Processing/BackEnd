package cn.ist.lowcoding.userservice.service;

import cn.ist.lowcoding.common.model.QueryCondition;
import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.common.util.JWTUtil;
import cn.ist.lowcoding.common.util.RequestUtil;
import cn.ist.lowcoding.userservice.model.User;
import cn.ist.lowcoding.userservice.pojo.dto.response.UserVO;
import cn.ist.lowcoding.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<User> findAll() {
        List<User> res = userRepo.findAll();
        return res;
    }

    public List<User> findByIds(List<String> ids) {
        return userRepo.findByIds(ids);
    }

    public List<User> query(List<QueryCondition> queryConditions) {
        return userRepo.queryByConditions(queryConditions);
    }

    public void register(String username, String password) {
        User user = User.Builder()
                .withUsername(username)
                .withPassword(password)
                .withRole(Role.Customer)
                .build();
        Optional<User> duplicateUser = userRepo.findByUsername(username);
        if (duplicateUser.isPresent()) {
            throw new RuntimeException("存在重名用户");
        } else {
            userRepo.save(user);
        }
    }

    public String login(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException("用户不存在");
        }
        if (!user.get().validatePlainTextPassword(password)) {
            throw new RuntimeException("用户名密码错误");
        }
        UserVO userVO = UserVO.from(user.get());
        return JWTUtil.encode(userVO.getId(), userVO.getRoles(), userVO.getUserFields());
    }

    public void authorize(String userId, List<Role> role) {
        Assert.isTrue(RequestUtil.getRoles().contains(Role.Admin), "只有管理员可以授权");
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("目标用户不存在"));
        user.authorize(role);
        userRepo.save(user);
    }
}
