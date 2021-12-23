package net.javaguides.springboot.services;

import net.javaguides.springboot.models.entity.Role;
import net.javaguides.springboot.models.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleEmail);
    User getUser(String email);
    List<User>getUsers();
}
