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
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;
import vn.haui.cntt.myproject.util.StandardizeStringUtil;
import vn.haui.cntt.myproject.util.VNCharacterUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdProductController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String MESSAGE = "message";

    @Autowired
    private final ProductService productService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProductImageService productImageService;
    @Autowired
    private final ProductCategoryService productCategoryService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;
    @Autowired
    private final ProductCommentService productCommentService;

    @GetMapping("/admin/products")
    public String viewListProducts(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
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
            Page<ProductCategory> pages = productCategoryService.listAll(pageStr, sortField, sortDir);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<ProductCategory> productCategoryList = pages.getContent();

            List<ProductImage> productImages = productImageService.listProductImage();
            List<Product> list = productService.listAll();
            List<Category> categories = categoryService.findAll();

            for (Product pr: list
            ) {
                for (ProductImage pi: productImages
                ) {
                    if(pi.getProduct().getId().equals(pr.getId())){
                        pi.setProduct(pr);
                    }
                }
            }

            for (ProductCategory pc : productCategoryList
                 ) {
                for (Product p : list
                     ) {
                    if(pc.getProduct().getId().equals(p.getId())){
                        pc.setProduct(p);
                    }
                }
            }

            for (ProductCategory pc : productCategoryList
                 ) {
                for (Category c : categories
                     ) {
                    if(pc.getCategory().getId().equals(c.getId())){
                        pc.setCategory(c);
                    }
                }
            }

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listProducts", productCategoryList);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

            return "admin/list-product";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/create")
    public String createProduct(RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Product product = new Product();
            List<Category> list = categoryService.findAll();

            Category category = new Category();

            model.addAttribute("user", loggedUser);
            model.addAttribute("newProduct", product);
            model.addAttribute("listCategories", list);
            model.addAttribute("category", category);

            return "admin/product-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/product/save")
    public String saveNewProduct(@ModelAttribute(name = "newProduct") Product product, RedirectAttributes redirectAttributes,
                                 @ModelAttribute(name = "category") Category category,
                                 @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                 @RequestParam("fileImage") MultipartFile[] multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        List<ProductImage> productImageList = new ArrayList<>();

        String productSearch = StandardizeStringUtil.standardizeString(product.getNameProduct());
        productSearch = VNCharacterUtil.removeAccent(productSearch);
        product.setProductSearch(productSearch);

        product.setCreatedDate(LocalDateTime.now());
        product.setPriceSale(product.getPrice());
        product.setCreatedBy(loggerUser.getUsername());
        product.setDeletedFlag(false);

        productService.save(product);

        for (MultipartFile m : multipartFile
        ) {
            ProductImage productImage = new ProductImage();
            if(!m.isEmpty()){
                String extraImg = StringUtils.cleanPath(m.getOriginalFilename());
                productImage.setPath(extraImg);
                productImageList.add(productImage);
            } else {
                productImage.setPath("blank_image.png");
                productImageList.add(productImage);
            }
        }

        for (ProductImage pi : productImageList
        ) {
            pi.setProduct(product);
            pi.setDeletedFlag(false);
            pi.setCreatedBy(loggerUser.getUsername());
            pi.setCreatedDate(LocalDateTime.now());
            productImageService.save(pi);
        }
        product.setProductImages(productImageList);

        ProductCategory productCategory = new ProductCategory();
        Category foundCategory = categoryService.findByName(category.getName());
        productCategory.setCategory(foundCategory);
        productCategory.setProduct(product);
        productCategory.setDeletedFlag(false);
        productCategory.setCreatedBy(loggerUser.getUsername());
        productCategory.setCreatedDate(LocalDateTime.now());
        productCategoryService.save(productCategory);

        String uploadDir = "product";

        for (MultipartFile m : multipartFile
        ) {
            if(!m.isEmpty()){
                String fileName = StringUtils.cleanPath(m.getOriginalFilename());
                imageService.uploadFile(uploadDir, m, fileName);
            }
        }

        redirectAttributes.addFlashAttribute(MESSAGE, "Sản phẩm đã được tạo.");

        return "redirect:/admin/products?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/product")
    public String getProduct(RedirectAttributes redirectAttributes,
                          @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                          @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Product product = productService.findById(id);
            List<ProductImage> productImage = productImageService.findById(product.getId());
            List<Category> categories = categoryService.findAll();
            ProductCategory pc = productCategoryService.findCategoryByProductId(product.getId());
            Category category = new Category();

            for (ProductImage pi : productImage
            ) {
                pi.setProduct(product);
            }

            model.addAttribute("user", loggedUser);
            model.addAttribute("foundProduct", product);
            model.addAttribute("listCategories", categories);
            model.addAttribute("productCategory", pc);
            model.addAttribute("category", category);

            return "admin/product-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundProduct") Product product, RedirectAttributes redirectAttributes,
                             @ModelAttribute(name = "category") Category category,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @RequestParam("fileImage") MultipartFile[] multipartFile,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            Product foundProduct = productService.findById(id);
            List<ProductImage> productImage = productImageService.findById(foundProduct.getId());
            int i = 0;
            for (MultipartFile m : multipartFile
                 ) {
                if(!m.isEmpty()){
                    String extraImg = StringUtils.cleanPath(m.getOriginalFilename());
                    productImage.get(i).setPath(extraImg);
                } else {
                    productImage.get(i).setPath(productImage.get(i).getPath());
                }
                productImage.get(i).setCreatedDate(LocalDateTime.now());
                productImage.get(i).setCreatedBy(loggerUser.getUsername());
                i++;
            }

            ProductCategory productCategory = productCategoryService.findCategoryByProductId(foundProduct.getId());
            Category foundCategory = categoryService.findByName(category.getName());
            productCategory.setCategory(foundCategory);
            productCategory.setUpdatedBy(loggerUser.getUsername());
            productCategory.setUpdatedDate(LocalDateTime.now());
            productCategoryService.save(productCategory);

            product.setProductSearch(foundProduct.getProductSearch());
            product.setCreatedBy(foundProduct.getCreatedBy());
            product.setCreatedDate(foundProduct.getCreatedDate());
            product.setUpdatedDate(LocalDateTime.now());
            product.setUpdatedBy(loggerUser.getUsername());
            product.setDeletedFlag(foundProduct.getDeletedFlag());

            productService.save(product);
            String uploadDir = "product";

            for (MultipartFile m : multipartFile
            ) {
                if(!m.isEmpty()){
                    String fileName = StringUtils.cleanPath(m.getOriginalFilename());
                    imageService.uploadFile(uploadDir, m, fileName);
                }
            }

            redirectAttributes.addFlashAttribute(MESSAGE, "Thông tin sản phẩm đã được cập nhật.");

            return "redirect:/admin/product?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/product/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            Product foundProduct = productService.findById(id);
            List<ProductImage> productImage = productImageService.findById(foundProduct.getId());
            ProductCategory productCategory = productCategoryService.findById(foundProduct.getId());
            List<ProductComment> productComments = productCommentService.findByProductId(foundProduct.getId());

            productCategoryService.deleteProductCategory(productCategory, loggerUser.getUsername());
            for (ProductImage pi : productImage
                 ) {
                productImageService.deleteProductImage(pi, loggerUser.getUsername());
            }
            for (ProductComment pc : productComments
            ) {
                productCommentService.deleteProductComment(pc, loggerUser.getUsername());
            }
            productService.deleteProduct(foundProduct, loggerUser.getUsername());

            redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");

            return "redirect:/admin/products?page=1&sortField=id&sortDir=asc";
        } catch (Exception e){
            return "404";
        }
    }
}
