package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.impl.UserServiceImpl;

@Controller
@RequiredArgsConstructor
public class AppController {

    @Autowired
    private final UserServiceImpl userService;


    @GetMapping("/home")
    public String viewHomePage(){
        return "user/index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        User newUser = new User();

        model.addAttribute("user", newUser);
        return "admin/auth-register-basic";
    }

    @PostMapping("/process-register")
    public String processRegistration(User user){
        try{
            userService.encodePassword(user);
            userService.isRoleUser(user);
            userService.setAvatarDefault(user);

            return "redirect:/login";
        } catch (Exception e) {
            return "404";
        }
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "user/about";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        return "user/contact-us";
    }
}
