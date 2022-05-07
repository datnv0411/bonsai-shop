package vn.haui.cntt.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.UserServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {

    @Autowired
    private final UserServiceImpl userService;


    @GetMapping("/home")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        User newUser = new User();

        model.addAttribute("user", newUser);
        return "register";
    }

    @PostMapping("/process-register")
    public String processRegistration(User user){
        try{
            userService.encodePassword(user);
            userService.isRoleUser(user);
            userService.setAvatarDefault(user);

            return "login";
        } catch (Exception e) {
            return "404";
        }
    }

    @GetMapping("/404")
    public String error403(){
        return "404";
    }

    @GetMapping("/logout")
    public String logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        try {
            authentication.setAuthenticated(false);
            return "redirect:/home";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/login")
    public String loginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        return "redirect:/home";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        return "contact-us";
    }
}
