package net.javaguides.springboot.services;

import net.javaguides.springboot.model.dto.UserRegistrationDto;
import net.javaguides.springboot.model.entity.Role;
import net.javaguides.springboot.model.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                registrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }
}
