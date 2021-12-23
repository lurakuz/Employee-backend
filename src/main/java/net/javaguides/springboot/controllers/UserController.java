package net.javaguides.springboot.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.models.entity.Role;
import net.javaguides.springboot.models.entity.User;
import net.javaguides.springboot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        log.info("Handled getting all users request");
        log.info("userService = {}", userService);
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        log.info("Handled saving user request");
        log.info("userService = {}", userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        log.info("Handled saving role request");
        log.info("userService = {}", userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        log.info("Handled adding role to user request");
        log.info("userService = {}", userService);
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();

    }
}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
