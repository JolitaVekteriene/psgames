package lt.codeacademy.psgames.controller;


import lt.codeacademy.psgames.dto.User;
import lt.codeacademy.psgames.service.UserService;
import lt.codeacademy.psgames.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/public/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserValidator userValidator;
    private final UserService userService;

    public UserController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/save")
    public String openUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "form/user";
    }

    @PostMapping("/save")
    public String createUser(@Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            log.debug("Cannot create user, has errors: {}", bindingResult.hasErrors());
            return "form/user";
        }

        userService.createUser(user);

        return "redirect:/public/users/save";
    }
}