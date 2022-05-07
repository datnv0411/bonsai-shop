package vn.haui.cntt.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/account")
    public String getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        try {
            return "my-account";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/account-detail")
    public String getUserDetailInformation(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            model.addAttribute("user", user);

            return "my-account-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/account/update")
    public String updateUserDetail(@ModelAttribute(name = "user") User user, RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                   @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
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

            user.setCreatedDate(loggedUser.getCreatedDate());
            user.setCreatedBy(loggedUser.getCreatedBy());
            user.setUpdatedBy(loggedUser.getUsername());
            user.setUpdatedDate(LocalDateTime.now());
            user.setDeletedFlag(loggedUser.getDeletedFlag());

            if (user.getPassword() == null || user.getPassword().equals("")){
                mUserService.updateAccountWithoutPassword(user);
            } else {
                mUserService.updateAccount(user);
            }

            loggerUser.setUsername(user.getUsername());

            redirectAttributes.addFlashAttribute("message", "Thông tin cá nhân đã được cập nhật.");

            return "redirect:/account-detail";
        } catch (Exception e){
            return "404";
        }
    }
}
