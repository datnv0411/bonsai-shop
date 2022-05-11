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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.service.AddressService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressService addressService;

    @Autowired
    private final UserService mUserService;

    @GetMapping("/address")
    public String listByPage(@AuthenticationPrincipal CustomUserDetailImpl loggedUser, Model model, @Param("page") int page,
                             @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        try {
            String email = loggedUser.getEmail();

            String pageStr = String.valueOf(page);
            Page<Address> pages = addressService.listAll(pageStr, sortField, sortDir, email);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Address> list = pages.getContent();

            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listAddress", list);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

            return "user/address";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/create-address")
    public String createAddress(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            Address address = new Address();

            model.addAttribute("newAddress", address);

            return "user/new-address";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/address/save")
    public String createAddressSuccess(@ModelAttribute(name = "address") Address address,
                                       @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                       RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            String email = loggerUser.getEmail();
            User user = mUserService.getByEmail(email);

            List<Address> addressList = addressService.findByUserId(user.getId());
            if(addressList.isEmpty()){
                address.setDefault(true);
            } else {
                address.setDefault(false);
            }

            address.setUser(user);
            address.setCreatedBy(loggerUser.getUsername());
            address.setCreatedDate(LocalDateTime.now());
            address.setDeletedFlag(false);

            addressService.save(address);

            redirectAttributes.addFlashAttribute("message", "Thêm mới địa chỉ thành công.");

            return "redirect:/address?page=1&sortField=is_default&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/update-address")
    public String UpdateAddress(Model model, @Param("addressId") Long addressId,
                                @AuthenticationPrincipal CustomUserDetailImpl loggerUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

       try {
           String email = loggerUser.getEmail();
           User user = mUserService.getByEmail(email);
           Address address = addressService.findByUserIdAndAddressId(user.getId(), addressId);

           model.addAttribute("address", address);

           return "user/update-address";
       } catch (Exception e){
           return "404";
       }
    }

    @PostMapping("/update-address/save")
    public String updateAddressSuccess(@ModelAttribute(name = "address") Address address,
                                @AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                RedirectAttributes redirectAttributes,
                                @Param("addressId") Long addressId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            address.setId(addressId);

            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            addressService.updateAddress(address, user);

            redirectAttributes.addFlashAttribute("message", "Thông tin địa chỉ đã được cập nhật.");

            return "redirect:/address?page=1&sortField=is_default&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/delete-address")
    public String deleteAddress(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                RedirectAttributes redirectAttributes,
                                @Param("addressId") Long addressId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            addressService.deleteAddress(addressId, user);

            redirectAttributes.addFlashAttribute("message", "Đã xóa địa chỉ thành công.");

            return "redirect:/address?page=1&sortField=is_default&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @RequestMapping("/default-address")
    public String setDefaultAddress(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                RedirectAttributes redirectAttributes,
                                @Param("addressId") Long addressId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            addressService.setDefaultAddress(addressId, user.getId());

            redirectAttributes.addFlashAttribute("message", "Thay đổi địa chỉ mặc định thành công.");

            return "redirect:/address?page=1&sortField=is_default&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }
}
