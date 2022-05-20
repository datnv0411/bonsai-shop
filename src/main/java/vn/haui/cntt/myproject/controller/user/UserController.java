package vn.haui.cntt.myproject.controller.user;

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

import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class UserController {
    private static final String LOGIN = "admin/auth-login-basic";

    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/account")
    public String getUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            return "user/my-account";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/account-detail")
    public String getUserDetailInformation(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            model.addAttribute("user", user);

            return "user/my-account-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/account/update")
    public String updateUserDetail(@ModelAttribute(name = "user") UserDto user, RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                   @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByUsername(username));

            List<UserDto> checkUser = mUserService.checkExistUser(user.getUsername(), user.getEmail(), user.getCellphone()).stream().map(UserMapper::toUserDto).collect(Collectors.toList());
            if (checkUser.size() > 1){
                redirectAttributes.addFlashAttribute("message", "Thông tin người dùng đã tồn tại.");
                return "redirect:/account-detail";
            }

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

            mUserService.updateAccountWithoutPassword(user.toUser(), loggedUser.getUsername());

            redirectAttributes.addFlashAttribute("message", "Thông tin cá nhân đã được cập nhật.");

            return "redirect:/account-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/password")
    public String viewChangPassword(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                    Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            model.addAttribute("user", user);

            return "user/change-password";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/password/save")
    public String changePassword(@ModelAttribute(name = "user") UserDto user, RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggerUser) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByUsername(username));

            boolean checkPass = mUserService.decodePass(user.getPassword(), loggedUser.toUser());

            if(!checkPass){
                redirectAttributes.addFlashAttribute("message", "Mật khẩu hiện tại không đúng.");

                return "redirect:/password";
            }

            loggedUser.setPassword(user.getResetPasswordToken());
            loggedUser.setUpdatedBy(username);
            loggedUser.setUpdatedDate(LocalDateTime.now());
            mUserService.encodePassword(loggedUser.toUser());

            redirectAttributes.addFlashAttribute("message", "Đã thay đổi mật khẩu.");

            return "redirect:/password";
        } catch (Exception e){
            return "404";
        }
    }
}
