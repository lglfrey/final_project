package com.example.springmodels.controllers;

import com.example.springmodels.models.modelUser;
import com.example.springmodels.clients.RoleClient;
import com.example.springmodels.clients.UserClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserClient userClient = new UserClient();
    private final RoleClient roleClient = new RoleClient();
    @GetMapping("/access_denied")
    private String access() {
        return "access-denied";
    }
    @GetMapping()
    public String index(HttpServletRequest request){
        List<GrantedAuthority> roles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (roles.get(0).getAuthority().equals("2"))
            return "redirect:/users";
        else if (roles.get(0).getAuthority().equals("3"))
            return "indexEmp";
        else if (roles.get(0).getAuthority().equals("4"))
            return "indexCrud";
        else if (roles.get(0).getAuthority().equals("1"))
            return "redirect:/views";

        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        modelUser user = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(getUsername())).findFirst().orElseThrow();
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        modelUser user = userClient.findAll()
                .stream().filter(u -> u.getUsername().equals(getUsername())).findFirst().orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleClient.findAll());
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") long id,
                         @RequestParam(name = "id_role") long id_role,
                         modelUser user) {
        user.setRole(roleClient.findById(id_role));
        userClient.update(user);
        return "redirect:/profile";
    }

    public static String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the username of the currently authenticated user
        String username = authentication.getName();
        return username;
    }
}
