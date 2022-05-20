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
import vn.haui.cntt.myproject.enums.ProductStatusEnum;
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
    private final UserService mUserService;
    @Autowired
    private final ImageServiceImpl imageService;
    @Autowired
    private final ProductCommentService productCommentService;
    @Autowired
    private final SupplierService supplierService;

    @GetMapping("/admin/products")
    public String viewListProducts(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                   Model model, @Param("page") int page,
                                   @Param("sortField") String sortField, @Param("sortDir") String sortDir,
                                   @Param(value = "keySearch") String keySearch){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            String pageStr = String.valueOf(page);
            Page<ProductDto> pages = productService.findAll(pageStr, sortField, sortDir, keySearch).map(ProductMapper::toProductDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<ProductDto> productList = pages.getContent();

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

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listProducts", productList);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keySearch", keySearch);

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
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            ProductDto product = new ProductDto();

            List<CategoryDto> list = categoryService.findAll()
                    .stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
            CategoryDto category = new CategoryDto();

            List<SupplierDto> supplierDtoList = supplierService.findAll()
                    .stream().map(SupplierMapper::toSupplierDto).collect(Collectors.toList());
            SupplierDto supplierDto = new SupplierDto();

            model.addAttribute("user", user);
            model.addAttribute("newProduct", product);
            model.addAttribute("listCategories", list);
            model.addAttribute("category", category);
            model.addAttribute("listSuppliers", supplierDtoList);
            model.addAttribute("supplier", supplierDto);

            return "admin/product-create";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/product/save")
    public String saveNewProduct(@ModelAttribute(name = "newProduct") ProductDto product, RedirectAttributes redirectAttributes,
                                 @ModelAttribute(name = "category") CategoryDto category,
                                 @ModelAttribute(name = "supplier") SupplierDto supplier,
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
        product.setStatus(ProductStatusEnum.Còn_hàng);
        product.setQuantity(Integer.parseInt(product.getImportQuantity()));
        product.setDeletedFlag(false);

        ProductDto foundProduct = ProductMapper.toProductDto(productService.save(product.toProduct()));

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
            pi.setProduct(foundProduct.toProduct());
            pi.setDeletedFlag(false);
            pi.setCreatedBy(loggerUser.getUsername());
            pi.setCreatedDate(LocalDateTime.now());
            productImageService.save(pi.toProductImage());
        }
        List<ProductImageDto> foundProductImage = productImageService.findById(foundProduct.getId())
                .stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());
            foundProduct.setProductImages(ProductImageMapper.toListProductImage(foundProductImage));

        CategoryDto foundCategory = CategoryMapper.toCategoryDto(categoryService.findByName(category.getName()));
        foundProduct.setCategory(foundCategory.toCategory());

        SupplierDto foundSupplier = SupplierMapper.toSupplierDto(supplierService.findByName(supplier.getNameSupplier()));
        foundProduct.setSupplier(foundSupplier.toSupplier());

        ProductDto saveProduct = ProductMapper.toProductDto(productService.save(foundProduct.toProduct()));

        String uploadDir = "product";

        for (MultipartFile m : multipartFile
        ) {
            if(!m.isEmpty()){
                String fileName = StringUtils.cleanPath(m.getOriginalFilename());
                imageService.uploadFile(uploadDir, m, fileName);
            }
        }

        redirectAttributes.addFlashAttribute(MESSAGE, "Sản phẩm đã được tạo.");

        return "redirect:/admin/products?page=1&sortField=id&sortDir=des&keySearch=";
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
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            ProductDto product = ProductMapper.toProductDto(productService.findById(id));
            List<ProductImageDto> productImage = productImageService.findById(product.getId())
                    .stream().map(ProductImageMapper::toProductImageDto).collect(Collectors.toList());

            List<CategoryDto> categories = categoryService.findAll()
                    .stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
            CategoryDto category = new CategoryDto();

            List<SupplierDto> supplierDtoList = supplierService.findAll()
                    .stream().map(SupplierMapper::toSupplierDto).collect(Collectors.toList());
            SupplierDto supplierDto = new SupplierDto();

            for (ProductImageDto pi : productImage
            ) {
                pi.setProduct(product.toProduct());
            }

            model.addAttribute("user", user);
            model.addAttribute("foundProduct", product);
            model.addAttribute("listCategories", categories);
            model.addAttribute("category", category);
            model.addAttribute("listSuppliers", supplierDtoList);
            model.addAttribute("supplier", supplierDto);

            return "admin/product-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundProduct") ProductDto product, RedirectAttributes redirectAttributes,
                             @ModelAttribute(name = "category") CategoryDto category,
                             @ModelAttribute(name = "supplier") SupplierDto supplier,
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

            CategoryDto foundCategory = CategoryMapper.toCategoryDto(categoryService.findByName(category.getName()));
            SupplierDto foundSupplier = SupplierMapper.toSupplierDto(supplierService.findByName(supplier.getNameSupplier()));

            product.setProductSearch(foundProduct.getProductSearch());
            product.setCreatedBy(foundProduct.getCreatedBy());
            product.setCreatedDate(foundProduct.getCreatedDate());
            product.setUpdatedDate(LocalDateTime.now());
            product.setUpdatedBy(loggerUser.getUsername());
            product.setDeletedFlag(foundProduct.getDeletedFlag());
            product.setCategory(foundCategory.toCategory());
            product.setSupplier(foundSupplier.toSupplier());
            product.setId(foundProduct.getId());
            product.setImportDate(foundProduct.getImportDate());
            product.setImportPrice(foundProduct.getImportPrice());
            product.setImportQuantity(foundProduct.getImportQuantity());

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
            List<ProductCommentDto> productComments = productCommentService.findByProductId(foundProduct.getId()).stream().map(ProductCommentMapper::toProductCommentDto).collect(Collectors.toList());

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

            return "redirect:/admin/products?page=1&sortField=id&sortDir=des&keySearch=";
        } catch (Exception e){
            return "404";
        }
    }
}
