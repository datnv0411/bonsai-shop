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
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.entity.Voucher;
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
    @Autowired
    private final VoucherService voucherService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/admin/vouchers")
    public String viewListVoucher(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                     Model model, @Param("page") int page,
                                     @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            String pageStr = String.valueOf(page);
            Page<Voucher> pages = voucherService.listAll(pageStr, sortField, sortDir);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Voucher> vouchers = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listVouchers", vouchers);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

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
            return "admin/auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Voucher voucher = new Voucher();


            model.addAttribute("user", loggedUser);
            model.addAttribute("newVoucher", voucher);

            return "admin/voucher-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/voucher/save")
    public String saveCategory(@ModelAttribute(name = "newVoucher") Voucher voucher, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                               @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
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

        voucherService.save(voucher);

        redirectAttributes.addFlashAttribute("message", "Voucher đã được tạo.");

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
            return "admin/auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Voucher voucher = voucherService.findByIdAndDeletedFlag(id);


            model.addAttribute("user", loggedUser);
            model.addAttribute("foundVoucher", voucher);

            return "admin/voucher-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/voucher/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundVoucher") Voucher voucher, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @RequestParam("fileImage") MultipartFile multipartFile,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
        Voucher foundVoucher = voucherService.findByIdAndDeletedFlag(id);
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

        voucherService.save(voucher);

        redirectAttributes.addFlashAttribute("message", "Thông tin voucher đã được cập nhật.");

        return "redirect:/admin/voucher?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/voucher/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
        Voucher voucher = voucherService.findByIdAndDeletedFlag(id);

        voucherService.delete(voucher);

        redirectAttributes.addFlashAttribute("message", "Đã xóa.");
        return "redirect:/admin/vouchers?page=1&sortField=id&sortDir=asc";
        } catch (Exception e){
            return "404";
        }
    }
}
