package com.example.springmodels.controllers;

import com.example.springmodels.clients.UserClient;
import com.example.springmodels.models.RegistrationRequest;
import com.example.springmodels.models.modelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
public class registrationController {
    private final UserClient userClient = new UserClient();
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    private String registration(@ModelAttribute("reg") RegistrationRequest registrationBody, Model model){
        model.addAttribute("reg", registrationBody);
        return "registration";
    }

    @PostMapping("/registration")
    private String reg(Model model, @Valid RegistrationRequest registrationBody, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("reg", registrationBody);
            return "registration";
        }

        modelUser userFromDB = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(registrationBody.getUsername())).findFirst().orElse(null);
        if (userFromDB != null){
            model.addAttribute("reg", registrationBody);
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "registration";
        }
        String regUrl = "http://localhost:8080/api/public/registration";
        ResponseEntity<String> response = restTemplate.exchange(
                regUrl,
                HttpMethod.POST,
                new HttpEntity<>(registrationBody),
                String.class
        );
        String responseBody = response.getBody();
        if (responseBody != null && responseBody.equals("success")) {
            return "redirect:/login";
        } else {
            model.addAttribute("reg", registrationBody);
            model.addAttribute("message", "Ошибка регистрации");
            return "registration";
        }
    }
}
