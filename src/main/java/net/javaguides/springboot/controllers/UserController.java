package net.javaguides.springboot.controllers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.mappers.UserMapper;
import net.javaguides.springboot.models.dto.UserDto;
import net.javaguides.springboot.models.entity.Role;
import net.javaguides.springboot.models.entity.User;
import net.javaguides.springboot.security.services.UserDetailsImpl;
import net.javaguides.springboot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserMapper userMapper;
    final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getUsers(){
        log.info("Handled getting all users request");
        log.info("userService = {}", userService);
        return ResponseEntity.ok().body(userMapper.map(userService.getUsers()));
    }

    @PostMapping("/save")
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

    @GetMapping("/logged")
    public ResponseEntity<UserDto> getLoggedUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(userService.getLoggedUser(userDetails));
    }
}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
