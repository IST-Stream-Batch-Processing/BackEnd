package cn.ist.lowcoding.userservice.model;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.ist.lowcoding.common.model.Role;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class User {
    public static SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, "LOW_CODING_PLATFORM".substring(0, 16)
            .getBytes(StandardCharsets.UTF_8));

    public static UserBuilder Builder() {
        return new UserBuilder();
    }

    String id;

    String username;

    String password;

    /**
     * 默认权限是Customer权限
     * Admin权限在平台初始化时自动创建
     */
    Set<Role> roles;

    public void authorize(List<Role> role) {
        this.roles = new HashSet<>(role);
    }

    public boolean validatePlainTextPassword(String plainText) {
        String encryptStr = aes.encryptHex(plainText);
        return encryptStr.equals(this.password);
    }

    public void addPlainTextPassword(String password) {
        this.password = aes.encryptHex(password);
    }

    public static class UserBuilder {
        User user;

        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder withUsername(String username) {
            this.user.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.user.addPlainTextPassword(password);
            return this;
        }

        public UserBuilder withRole(Role role) {
            if (this.user.roles == null) {
                this.user.roles = new HashSet<>();
            }
            this.user.roles.add(role);
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}
