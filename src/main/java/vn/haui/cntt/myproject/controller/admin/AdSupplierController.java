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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.dto.CategoryDto;
import vn.haui.cntt.myproject.dto.SupplierDto;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.CategoryMapper;
import vn.haui.cntt.myproject.mapper.SupplierMapper;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.CategoryService;
import vn.haui.cntt.myproject.service.SupplierService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdSupplierController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String MESSAGE = "message";

    @Autowired
    private final SupplierService supplierService;
    @Autowired
    private final UserService mUserService;

    @GetMapping("/admin/suppliers")
    public String viewListCategories(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
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
            Page<SupplierDto> pages = supplierService.listAll(pageStr, sortField, sortDir, keySearch).map(SupplierMapper::toSupplierDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<SupplierDto> supplierDtoList = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listSuppliers", supplierDtoList);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keySearch", keySearch);

            return "admin/list-supplier";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/supplier-create")
    public String createCategory(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            SupplierDto supplierDto = new SupplierDto();


            model.addAttribute("user", user);
            model.addAttribute("newSupplier", supplierDto);

            return "admin/supplier-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/supplier/save")
    public String saveCategory(@ModelAttribute(name = "newSupplier") SupplierDto supplierDto, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            supplierDto.setCreatedDate(LocalDateTime.now());
            supplierDto.setCreatedBy(loggerUser.getUsername());
            supplierDto.setDeletedFlag(false);
            supplierService.save(supplierDto.toSupplier());

        redirectAttributes.addFlashAttribute(MESSAGE, "Nhà cung cấp đã được thêm.");

        return "redirect:/admin/suppliers?page=1&sortField=id&sortDir=des&keySearch=";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/supplier")
    public String getProduct(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            SupplierDto supplierDto = SupplierMapper.toSupplierDto(supplierService.findById(id));


            model.addAttribute("user", user);
            model.addAttribute("foundSupplier", supplierDto);

            return "admin/supplier-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/supplier/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundCategory") SupplierDto supplierDto, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            SupplierDto foundSupplier = SupplierMapper.toSupplierDto(supplierService.findById(id));
            supplierDto.setId(foundSupplier.getId());
            supplierDto.setUpdatedDate(LocalDateTime.now());
            supplierDto.setUpdatedBy(loggerUser.getUsername());
            supplierDto.setDeletedFlag(foundSupplier.getDeletedFlag());
            supplierDto.setCreatedDate(foundSupplier.getCreatedDate());
            supplierDto.setCreatedBy(foundSupplier.getCreatedBy());
            supplierService.save(supplierDto.toSupplier());

        redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin nhà cung cấp đã được cập nhật.");

        return "redirect:/admin/supplier?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/supplier/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            SupplierDto foundSupplier = SupplierMapper.toSupplierDto(supplierService.findById(id));
            String username = loggerUser.getUsername();

            supplierService.delete(foundSupplier.toSupplier(), username);

            redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");
            return "redirect:/admin/suppliers?page=1&sortField=id&sortDir=des&keySearch=";
        } catch (Exception e){
            return "404";
        }
    }
}
