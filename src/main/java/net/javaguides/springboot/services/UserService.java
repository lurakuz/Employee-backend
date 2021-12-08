package net.javaguides.springboot.services;

import net.javaguides.springboot.model.dto.UserRegistrationDto;
import net.javaguides.springboot.model.entity.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);

}
