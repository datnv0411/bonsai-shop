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
import vn.haui.cntt.myproject.dto.*;
import vn.haui.cntt.myproject.mapper.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.service.impl.ImageServiceImpl;
import vn.haui.cntt.myproject.util.StandardizeStringUtil;
import vn.haui.cntt.myproject.util.VNCharacterUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            String pageStr = String.valueOf(page);
            Page<ProductCategoryDto> pages = productCategoryService.listAll(pageStr, sortField, sortDir).map(ProductCategoryMapper::toProductCategoryDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<ProductCategoryDto> productCategoryList = pages.getContent();

            List<ProductImageDto> productImages = productImageService.listProductImage().stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());
            List<ProductDto> list = productService.listAll().stream().map(ProductMapper::toProductDto).collect(Collectors.toList());
            List<CategoryDto> categories = categoryService.findAll().stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());

            for (ProductDto pr: list
            ) {
                for (ProductImageDto pi: productImages
                ) {
                    if(pi.getProduct().getId().equals(pr.getId())){
                        pi.setProduct(pr.toProduct());
                    }
                }
            }

            for (ProductCategoryDto pc : productCategoryList
                 ) {
                for (ProductDto p : list
                     ) {
                    if(pc.getProduct().getId().equals(p.getId())){
                        pc.setProduct(p.toProduct());
                    }
                }
            }

            for (ProductCategoryDto pc : productCategoryList
                 ) {
                for (CategoryDto c : categories
                     ) {
                    if(pc.getCategory().getId().equals(c.getId())){
                        pc.setCategory(c.toCategory());
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
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            ProductDto product = new ProductDto();
            List<CategoryDto> list = categoryService.findAll().stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());

            CategoryDto category = new CategoryDto();

            model.addAttribute("user", user);
            model.addAttribute("newProduct", product);
            model.addAttribute("listCategories", list);
            model.addAttribute("category", category);

            return "admin/product-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/product/save")
    public String saveNewProduct(@ModelAttribute(name = "newProduct") ProductDto product, RedirectAttributes redirectAttributes,
                                 @ModelAttribute(name = "category") CategoryDto category,
                                 @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                 @RequestParam("fileImage") MultipartFile[] multipartFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        List<ProductImageDto> productImageList = new ArrayList<>();

        String productSearch = StandardizeStringUtil.standardizeString(product.getNameProduct());
        productSearch = VNCharacterUtil.removeAccent(productSearch);
        product.setProductSearch(productSearch);

        product.setCreatedDate(LocalDateTime.now());
        product.setPriceSale(product.getPrice());
        product.setCreatedBy(loggerUser.getUsername());
        product.setDeletedFlag(false);

        productService.save(product.toProduct());

        for (MultipartFile m : multipartFile
        ) {
            ProductImageDto productImage = new ProductImageDto();
            if(!m.isEmpty()){
                String extraImg = StringUtils.cleanPath(m.getOriginalFilename());
                productImage.setPath(extraImg);
                productImageList.add(productImage);
            } else {
                productImage.setPath("blank_image.png");
                productImageList.add(productImage);
            }
        }

        for (ProductImageDto pi : productImageList
        ) {
            pi.setProduct(product.toProduct());
            pi.setDeletedFlag(false);
            pi.setCreatedBy(loggerUser.getUsername());
            pi.setCreatedDate(LocalDateTime.now());
            productImageService.save(pi.toProductImage());
        }
        product.setProductImages(ProductImageMapper.toListProductImage(productImageList));

        ProductCategoryDto productCategory = new ProductCategoryDto();
        CategoryDto foundCategory = CategoryMapper.toCategoryDto(categoryService.findByName(category.getName()));
        productCategory.setCategory(foundCategory.toCategory());
        productCategory.setProduct(product.toProduct());
        productCategory.setDeletedFlag(false);
        productCategory.setCreatedBy(loggerUser.getUsername());
        productCategory.setCreatedDate(LocalDateTime.now());
        productCategoryService.save(productCategory.toProductCategory());

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
    public String getProduct(@AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                          @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            ProductDto product = ProductMapper.toProductDto(productService.findById(id));
            List<ProductImageDto> productImage = productImageService.findById(product.getId()).stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());
            List<CategoryDto> categories = categoryService.findAll().stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
            ProductCategoryDto pc = ProductCategoryMapper.toProductCategoryDto(productCategoryService.findCategoryByProductId(product.getId()));
            CategoryDto category = new CategoryDto();

            for (ProductImageDto pi : productImage
            ) {
                pi.setProduct(product.toProduct());
            }

            model.addAttribute("user", user);
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
    public String updateUser(@ModelAttribute(name = "foundProduct") ProductDto product, RedirectAttributes redirectAttributes,
                             @ModelAttribute(name = "category") CategoryDto category,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @RequestParam("fileImage") MultipartFile[] multipartFile,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            ProductDto foundProduct = ProductMapper.toProductDto(productService.findById(id));
            List<ProductImageDto> productImage = productImageService.findById(foundProduct.getId()).stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());
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

            ProductCategoryDto productCategory = ProductCategoryMapper.toProductCategoryDto(productCategoryService.findCategoryByProductId(foundProduct.getId()));
            CategoryDto foundCategory = CategoryMapper.toCategoryDto(categoryService.findByName(category.getName()));
            productCategory.setCategory(foundCategory.toCategory());
            productCategory.setUpdatedBy(loggerUser.getUsername());
            productCategory.setUpdatedDate(LocalDateTime.now());
            productCategoryService.save(productCategory.toProductCategory());

            product.setProductSearch(foundProduct.getProductSearch());
            product.setCreatedBy(foundProduct.getCreatedBy());
            product.setCreatedDate(foundProduct.getCreatedDate());
            product.setUpdatedDate(LocalDateTime.now());
            product.setUpdatedBy(loggerUser.getUsername());
            product.setDeletedFlag(foundProduct.getDeletedFlag());

            productService.save(product.toProduct());
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
            ProductDto foundProduct = ProductMapper.toProductDto(productService.findById(id));
            List<ProductImageDto> productImage = productImageService.findById(foundProduct.getId()).stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());
            ProductCategoryDto productCategory = ProductCategoryMapper.toProductCategoryDto(productCategoryService.findById(foundProduct.getId()));
            List<ProductCommentDto> productComments = productCommentService.findByProductId(foundProduct.getId()).stream().map(ProductCommentMapper::toProductCommentDto).collect(Collectors.toList());

            productCategoryService.deleteProductCategory(productCategory.toProductCategory(), loggerUser.getUsername());
            for (ProductImageDto pi : productImage
                 ) {
                productImageService.deleteProductImage(pi.toProductImage(), loggerUser.getUsername());
            }
            for (ProductCommentDto pc : productComments
            ) {
                productCommentService.deleteProductComment(pc.toProductComment(), loggerUser.getUsername());
            }
            productService.deleteProduct(foundProduct.toProduct(), loggerUser.getUsername());

            redirectAttributes.addFlashAttribute(MESSAGE, "Đã xóa.");

            return "redirect:/admin/products?page=1&sortField=id&sortDir=asc";
        } catch (Exception e){
            return "404";
        }
    }
}
