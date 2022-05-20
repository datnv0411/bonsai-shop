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
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.dto.VoucherDto;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.mapper.VoucherMapper;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.VoucherService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdVoucherController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String MESSAGE = "message";

    @Autowired
    private final VoucherService voucherService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/admin/vouchers")
    public String viewListVoucher(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                     Model model, @Param("page") int page, @Param(value = "keySearch") String keySearch,
                                     @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            String pageStr = String.valueOf(page);
            Page<VoucherDto> pages = voucherService.listAll(pageStr, sortField, sortDir, keySearch).map(VoucherMapper::toVoucherDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<VoucherDto> vouchers = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listVouchers", vouchers);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keySearch", keySearch);

            return "admin/list-voucher";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/voucher-create")
    public String createCategory(RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                 Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByUsername(username));

            VoucherDto voucher = new VoucherDto();


            model.addAttribute("user", loggedUser);
            model.addAttribute("newVoucher", voucher);

            return "admin/voucher-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/voucher/save")
    public String saveCategory(@ModelAttribute(name = "newVoucher") VoucherDto voucher, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                               @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        voucher.setCreatedDate(LocalDateTime.now());
        voucher.setCreatedBy(loggerUser.getUsername());
        voucher.setTimesOfUse(1);

        voucher.setTitle("Giảm " + voucher.getPercentValue() + "% tối đa " + voucher.getUpToValue() + " cho đơn hàng từ "+ voucher.getApplicableValue() +" trở lên");

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            voucher.setImage(fileName);

            String uploadDir = "voucher";
            imageService.uploadFile(uploadDir, multipartFile, fileName);
        } else {
            voucher.setImage("blank_image.png");
        }

        voucherService.save(voucher.toVoucher());

        redirectAttributes.addFlashAttribute(MESSAGE, "Voucher đã được tạo.");

        return "redirect:/admin/vouchers?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/voucher")
    public String getProduct(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto loggedUser = UserMapper.toUserDto(mUserService.getByUsername(username));

            VoucherDto voucher = VoucherMapper.toVoucherDto(voucherService.findByIdAndDeletedFlag(id));


            model.addAttribute("user", loggedUser);
            model.addAttribute("foundVoucher", voucher);

            return "admin/voucher-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/voucher/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundVoucher") VoucherDto voucher, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @RequestParam("fileImage") MultipartFile multipartFile,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        VoucherDto foundVoucher = VoucherMapper.toVoucherDto(voucherService.findByIdAndDeletedFlag(id));
        voucher.setId(foundVoucher.getId());
        voucher.setTimesOfUse(foundVoucher.getTimesOfUse());
        voucher.setTitle(foundVoucher.getTitle());
        voucher.setCreatedBy(foundVoucher.getCreatedBy());
        voucher.setCreatedDate(foundVoucher.getCreatedDate());
        voucher.setUpdatedBy(loggerUser.getUsername());
        voucher.setUpdatedDate(LocalDateTime.now());
        voucher.setDeletedFlag(foundVoucher.getDeletedFlag());

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            voucher.setImage(fileName);

            String uploadDir = "voucher";
            imageService.uploadFile(uploadDir, multipartFile, fileName);
        } else {
            voucher.setImage(foundVoucher.getImage());
        }

        voucherService.save(voucher.toVoucher());

        redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin voucher đã được cập nhật.");

        return "redirect:/admin/voucher?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/voucher/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        VoucherDto voucher = VoucherMapper.toVoucherDto(voucherService.findByIdAndDeletedFlag(id));

        voucherService.delete(voucher.toVoucher());

        redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");
        return "redirect:/admin/vouchers?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }
}
