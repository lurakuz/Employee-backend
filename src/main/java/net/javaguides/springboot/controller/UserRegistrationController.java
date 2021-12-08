package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.model.dto.UserRegistrationDto;
import net.javaguides.springboot.services.UserServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class UserRegistrationController {

    private UserServiceImpl userServiceImpl;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        userServiceImpl.save(registrationDto);
        return "redirect:/registration?success";
    }

}
