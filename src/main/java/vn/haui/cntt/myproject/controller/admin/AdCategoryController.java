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
import vn.haui.cntt.myproject.entity.Category;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.CategoryService;
import vn.haui.cntt.myproject.service.ProductCategoryService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdCategoryController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String MESSAGE = "message";

    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ProductCategoryService productCategoryService;

    @GetMapping("/admin/categories")
    public String viewListCategories(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                     Model model, @Param("page") int page,
                                     @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            String pageStr = String.valueOf(page);
            Page<Category> pages = categoryService.listAll(pageStr, sortField, sortDir);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Category> categories = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listCategories", categories);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
        } catch (Exception e){
            return "404";
        }

        return "admin/list-category";
    }

    @GetMapping("/admin/category-create")
    public String createCategory(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Category category = new Category();


            model.addAttribute("user", loggedUser);
            model.addAttribute("newCategory", category);

            return "admin/category-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/category/save")
    public String saveCategory(@ModelAttribute(name = "newCategory") Category category, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        category.setCreatedDate(LocalDateTime.now());
        category.setCreatedBy(loggerUser.getUsername());
        category.setDeletedFlag(false);
        categoryService.save(category);

        redirectAttributes.addFlashAttribute(MESSAGE, "Danh mục đã được tạo.");

        return "redirect:/admin/categories?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/category")
    public String getProduct(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Category category = categoryService.findById(id);


            model.addAttribute("user", loggedUser);
            model.addAttribute("foundCategory", category);

            return "admin/category-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/category/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundCategory") Category category, RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        Category foundCategory = categoryService.findById(id);
        category.setId(foundCategory.getId());
        category.setUpdatedDate(LocalDateTime.now());
        category.setUpdatedBy(loggerUser.getUsername());
        category.setDeletedFlag(foundCategory.getDeletedFlag());
        categoryService.save(category);

        redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin danh mục đã được cập nhật.");

        return "redirect:/admin/category?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/category/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            Category foundCategory = categoryService.findById(id);
            String username = loggerUser.getUsername();

            categoryService.delete(foundCategory, username);

            redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");
            return "redirect:/admin/categories?page=1&sortField=id&sortDir=asc";
        } catch (Exception e){
            return "404";
        }
    }
}
