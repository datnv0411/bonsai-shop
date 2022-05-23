package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

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
        UserDto newUser = new UserDto();

        model.addAttribute("user", newUser);
        return "admin/auth-register-basic";
    }
 
    @PostMapping("/process-register")
    public String processRegistration(@ModelAttribute(name = "user") UserDto user, RedirectAttributes redirectAttributes){
        try{
            List<UserDto> checkUser = userService.checkExistUser(user.getUsername(), user.getEmail(), user.getCellphone()).stream().map(UserMapper::toUserDto).collect(Collectors.toList());
            if (checkUser.size() > 0){
                redirectAttributes.addFlashAttribute("message", "Người dùng đã tồn tại.");
                return "redirect:/register";
            }
            userService.encodePassword(user.toUser());
            UserDto foundUser = UserMapper.toUserDto(userService.getByUsername(user.getUsername()));
            userService.isRoleUser(foundUser.toUser());
            userService.setAvatarDefault(foundUser.toUser());

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
