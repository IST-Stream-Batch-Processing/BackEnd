package cn.ist.lowcoding.userservice.component;

import cn.ist.lowcoding.common.initializer.Initializer;
import cn.ist.lowcoding.common.model.Role;
import cn.ist.lowcoding.userservice.model.User;
import cn.ist.lowcoding.userservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminInitializer implements Initializer {
    @Autowired
    UserRepo userRepo;

    @Override
    public void run() {
        List<User> userList = userRepo.findByRole(Role.Admin);
        if (userList.isEmpty()) {
            User admin = User.Builder()
                    .withUsername("admin")
                    .withPassword("admin")
                    .withRole(Role.Admin)
                    .build();
            userRepo.save(admin);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
