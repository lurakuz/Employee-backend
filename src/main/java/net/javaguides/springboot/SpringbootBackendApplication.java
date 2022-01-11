package net.javaguides.springboot;

import net.javaguides.springboot.models.entity.ERole;
import net.javaguides.springboot.models.entity.Role;
import net.javaguides.springboot.models.entity.User;
import net.javaguides.springboot.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringbootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, ERole.ROLE_USER));
//            userService.saveRole(new Role(null, ERole.ROLE_ADMIN));
//            userService.saveRole(new Role(null, ERole.ROLE_MANAGER));
//
//            userService.saveUser(new User("Yurii", "Kuzyk", "kuz9", "luraku08@gmail.com", "qweqwe112"));
//            userService.saveUser(new User("John", "Travolta", "john", "travolta@gmail.com", "1234"));
//            userService.saveUser(new User("Will", "Smith", "will", "smith@gmail.com", "1234"));
//            userService.saveUser(new User("Jim", "Carry", "jim", "carry@gmail.com", "1234"));
//            userService.saveUser(new User("Arnold", "Schwarzenegger", "arnold", "schwarznegger@gmail.com", "1234"));
//
//            userService.addRoleToUser("kuz9", "ROLE_ADMIN");
//            userService.addRoleToUser("john", "ROLE_MANAGER");
//            userService.addRoleToUser("will", "ROLE_USER");
//            userService.addRoleToUser("jim", "ROLE_USER");
//            userService.addRoleToUser("arnold", "ROLE_USER");
//        };
//    }
}
