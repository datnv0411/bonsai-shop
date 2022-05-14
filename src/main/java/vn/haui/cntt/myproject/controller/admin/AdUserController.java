package vn.haui.cntt.myproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import vn.haui.cntt.myproject.dto.RoleDto;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.RoleMapper;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.RoleService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdUserController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String MESSAGE = "message";
    private static final String RETURN_URL = "redirect:/admin/users?page=1&sortField=id&sortDir=asc";

    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;
    @Autowired
    private final RoleService roleService;

    @GetMapping("/admin/users")
    public String viewListUsers(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                Model model, @Param("page") int page,
                                @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            String pageStr = String.valueOf(page);
            Page<UserDto> pages = mUserService.listAll(pageStr, sortField, sortDir).map(UserMapper::toUserDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<UserDto> list = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listUsers", list);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

            return "admin/list-user";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/create-user")
    public String createUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggerUser.getEmail();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByEmail(email));

            UserDto user = new UserDto();
            RoleDto role = new RoleDto();

            model.addAttribute("user", loggedUser);
            model.addAttribute("newUser", user);
            model.addAttribute("role", role);

            return "admin/user-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute(name = "newUser") UserDto user, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @ModelAttribute(name = "role") RoleDto role,
                             @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        RoleDto foundRole = RoleMapper.toRoleDto(roleService.findByName(role.getName()));
        mUserService.encodePassword(user.toUser());
        mUserService.save(user.toUser(), foundRole.toRole(), loggerUser.getUsername());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setAvatar(fileName);
        } else {
            user.setAvatar("avatar-default.png");
        }
        String uploadDir = "user/" + user.getId();
        imageService.uploadFile(uploadDir, multipartFile, user.getAvatar());
        mUserService.saveUser(user.toUser());

        redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin người dùng đã được cập nhật.");

        return RETURN_URL;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/{id}")
    public String getUser(RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                          @PathVariable(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByEmail(email));

            UserDto foundUser = UserMapper.toUserDto(mUserService.findById(id));
            RoleDto role = RoleMapper.toRoleDto(roleService.findByUserId(id));

            model.addAttribute("user", loggedUser);
            model.addAttribute("role", role);
            model.addAttribute("foundUser", foundUser);

            return "admin/user-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundUser") UserDto user, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @ModelAttribute(name = "role") RoleDto role,
                             @RequestParam("fileImage") MultipartFile multipartFile,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            UserDto foundUser = UserMapper.toUserDto(mUserService.findById(id));
            RoleDto foundRole = RoleMapper.toRoleDto(roleService.findByName(role.getName()));
            user.addRole(foundRole.toRole());

            if(!multipartFile.isEmpty()){
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                user.setAvatar(fileName);

                String uploadDir = "user/" + user.getId();
                imageService.uploadFile(uploadDir, multipartFile, fileName);
            } else {
                user.setAvatar(foundUser.getAvatar());
            }

            if (user.getPassword() == null || user.getPassword().equals("")){
                mUserService.updateAccountWithoutPassword(user.toUser(), loggerUser.getUsername());
            } else {
                mUserService.updateAccount(user.toUser(), loggerUser.getUsername());
            }

            redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin người dùng đã được cập nhật.");

            return RETURN_URL;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            UserDto foundUser = UserMapper.toUserDto(mUserService.findById(id));

            mUserService.deleteUser(foundUser.toUser(), loggerUser.getUsername());

            redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");

            return RETURN_URL;
        } catch (Exception e){
            return "404";
        }
    }
}
