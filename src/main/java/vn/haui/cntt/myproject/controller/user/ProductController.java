package vn.haui.cntt.myproject.controller.user;

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
import org.springframework.web.bind.annotation.*;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProductImageService productImageService;
    @Autowired
    private final ProductCategoryService productCategoryService;
    @Autowired
    private final ProductCommentService productCommentService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final OrderService orderService;

    @RequestMapping("/list-product")
    public String viewProduct(Model model){
        return listByPage(model, 1, "id", "des", "");
    }

    @GetMapping("/product")
    public String listByPage(Model model, @Param("page") int page,
                             @Param("sortField") String sortField, @Param("sortDir") String sortDir,
                             @Param("categoryId") String categoryId){

        try {
            String pageStr = String.valueOf(page);
            Page<Product> pages = productService.listAll(pageStr, sortField, sortDir, categoryId);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Product> list = pages.getContent();
            List<Category> categories = categoryService.findAll();
            List<ProductImage> productImages = productImageService.listProductImage();

            for (Product pr: list
            ) {
                for (ProductImage pi: productImages
                ) {
                    if(pi.getProduct().getId().equals(pr.getId())){
                        pi.setProduct(pr);
                    }
                }
            }

            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listProducts", list);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("categories", categories);
            model.addAttribute("categoryId", categoryId);

            return "user/shop";
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/search")
    public String searchProduct(Model model, @Param("nameSearch") String nameSearch, @Param("page") int page,
                             @Param("sortField") String sortField, @Param("sortDir") String sortDir,
                                @Param(value = "categoryId") String categoryId){

        try {
            String pageStr = String.valueOf(page);
            List<Category> categories = categoryService.findAll();
            Page<Product> pages = productService.getProductByProductSearch(nameSearch, pageStr, sortField, sortDir, categoryId);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Product> list = pages.getContent();

            model.addAttribute("nameSearch", nameSearch);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listProducts", list);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("categories", categories);
            model.addAttribute("categoryId", categoryId);

            return "user/shop";
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/product-detail")
    public String viewDetailProduct(Model model, @Param(value = "productId") Long productId,
                                    @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        try {
            Product product = productService.findById(productId);

            Long categoryId = productCategoryService.findCategoryByProductId(productId).getCategory().getId();

            List<Product> list = productService.findByCategoryId(categoryId);

            List<ProductComment> productComments = productCommentService.findAll(productId);

            ProductComment newProductComment = new ProductComment();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)){
                String email = loggedUser.getEmail();
                User user = mUserService.getByEmail(email);

                boolean isBought = orderService.isBought(user.getId(), productId, "Đã_giao");

                model.addAttribute("logUser", user);
                model.addAttribute("isBought", isBought);
            }

            model.addAttribute("foundProduct", product);
            model.addAttribute("listProduct", list);
            model.addAttribute("comments", productComments);
            model.addAttribute("newComment", newProductComment);

            return "user/shop-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/product/comment")
    public String commentProduct(@Param(value = "productId") Long productId,
                                 @AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                 @ModelAttribute(name = "newComment") ProductComment productComment){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            Product product = productService.findById(productId);

            productComment.setProduct(product);
            productComment.setUser(user);
            productComment.setDeletedFlag(false);
            productComment.setCreatedBy(user.getUsername());
            productComment.setCreatedDate(LocalDateTime.now());

            productCommentService.save(productComment);

            return "redirect:/product-detail?productId=" + productId;
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/product/remove-comment")
    public String removeComment(@Param(value = "id") Long id,
                                @AuthenticationPrincipal CustomUserDetailImpl loggedUser){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            ProductComment productComment = productCommentService.findById(id);
            Long productId = productComment.getProduct().getId();
            productComment.setDeletedFlag(true);
            productComment.setUpdatedDate(LocalDateTime.now());
            productComment.setUpdatedBy(loggedUser.getUsername());
            productCommentService.save(productComment);

            return "redirect:/product-detail?productId=" + productId;
        } catch (Exception e){
            return "404";
        }
    }
}
