package vn.haui.cntt.myproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdAccountController {
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/admin/account")
    public String viewMyAccount(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            model.addAttribute("user", user);

            return "admin/pages-account-settings-account";
        } catch (Exception e){
            return "404";
        }
    }



    @PostMapping("/admin/account/update")
    public String updateUserDetail(@ModelAttribute(name = "user") User user, RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                   @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            if(!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                user.setAvatar(fileName);

                String uploadDir = "user/" + user.getId();
                imageService.uploadFile(uploadDir, multipartFile, fileName);
            } else {
                user.setAvatar(loggedUser.getAvatar());
            }

            if (user.getPassword() == null || user.getPassword().equals("")){
                mUserService.updateAccountWithoutPassword(user, loggerUser.getUsername());
            } else {
                mUserService.updateAccount(user, loggerUser.getUsername());
            }

            loggerUser.setUsername(user.getUsername());

            redirectAttributes.addFlashAttribute("message", "Thông tin cá nhân đã được cập nhật.");

            return "redirect:/admin/account";
        } catch (Exception e){
            return "404";
        }
    }
}
