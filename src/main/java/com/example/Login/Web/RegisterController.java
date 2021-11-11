package com.example.Login.Web;

import com.example.Login.Service.UserService;
import com.example.Login.Web.RegisterDataTransferObj;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public RegisterDataTransferObj registerDataTransferObj() {
        return new RegisterDataTransferObj();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegisterDataTransferObj registerDataTransferObj) {
        userService.save(registerDataTransferObj);
        return "redirect:/registration?success";
    }
}
