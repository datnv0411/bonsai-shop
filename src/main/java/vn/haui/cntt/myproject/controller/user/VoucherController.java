package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.VoucherService;

@RestController
@RequiredArgsConstructor
public class VoucherController {
    @Autowired
    private final VoucherService voucherService;
    @Autowired
    private final UserService mUserService;

    @PostMapping("/voucher")
    public String viewVoucher(@Param (value = "voucherCode") String voucherCode,
                              @Param (value = "subTotal") Integer subTotal,
                              Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        Integer discount = voucherService.applyVoucher(voucherCode, subTotal);

        model.addAttribute("check", discount);

        return String.valueOf(discount);
    }
}
