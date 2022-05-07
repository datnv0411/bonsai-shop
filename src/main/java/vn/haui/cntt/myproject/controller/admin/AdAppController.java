package vn.haui.cntt.myproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.haui.cntt.myproject.entity.PaymentOrder;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.PaymentOrderService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdAppController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final PaymentOrderService paymentOrderService;

    @GetMapping("/admin/home")
    public String viewHomePage(@AuthenticationPrincipal CustomUserDetailImpl loggerUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }
        try {
            String email = loggerUser.getEmail();
            User loggedUser = userService.getByEmail(email);
            List<PaymentOrder> paymentOrder1 = paymentOrderService.findByStatus("Đã thanh toán", "2022-01-01", "2022-01-31");
            List<PaymentOrder> paymentOrder2 = paymentOrderService.findByStatus("Đã thanh toán", "2022-02-01", "2022-02-28");
            List<PaymentOrder> paymentOrder3 = paymentOrderService.findByStatus("Đã thanh toán", "2022-03-01", "2022-03-31");
            List<PaymentOrder> paymentOrder4 = paymentOrderService.findByStatus("Đã thanh toán", "2022-04-01", "2022-04-30");
            List<PaymentOrder> paymentOrder5 = paymentOrderService.findByStatus("Đã thanh toán", "2022-05-01", "2022-05-31");
            List<PaymentOrder> paymentOrder6 = paymentOrderService.findByStatus("Đã thanh toán", "2022-06-01", "2022-06-30");

            List money = new ArrayList();

            Long money1 = 0l;
            Long money2 = 0l;
            Long money3 = 0l;
            Long money4 = 0l;
            Long money5 = 0l;
            Long money6 = 0l;

            for (PaymentOrder po : paymentOrder1
            ) {
                money1 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder2
            ) {
                money2 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder3
                 ) {
                money3 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder4
            ) {
                money4 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder5
            ) {
                money5 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder6
            ) {
                money6 += po.getTotalPaid();
            }
            Long moneyAll = money1 + money2 + money3 + money4 + money5 + money6;
            money.add(money1);
            money.add(money2);
            money.add(money3);
            money.add(money4);
            money.add(money5);
            money.add(money6);
            model.addAttribute("user", loggedUser);
            model.addAttribute("paymentOrder", money);
            model.addAttribute("total", moneyAll);

            return "admin/ad-index";
        } catch (Exception e){
            return "404";
        }
    }

//    @GetMapping("/admin/register")
//    public String showSignUpForm(Model model){
//        User newUser = new User();
//
//        model.addAttribute("user", newUser);
//        return "auth-register-basic";
//    }
//
//    @PostMapping("/admin/process-register")
//    public String processRegistration(User user) {
//        userService.encodePassword(user);
//        userService.isRoleAdmin(user);
//        userService.setAvatarDefault(user);
//
//        return "auth-login-basic";
//    }

    @GetMapping("/404")
    public String error404(){
        return "404";
    }

    @GetMapping("/admin/logout")
    public String logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            authentication.setAuthenticated(false);
            return "redirect:/login";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/logout")
    public String userLogout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            authentication.setAuthenticated(false);
            return "redirect:/login";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/doLogin")
    public String doLogin(@AuthenticationPrincipal CustomUserDetailImpl loggerUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        String email = loggerUser.getEmail();
        User loggedUser = userService.getByEmail(email);

        boolean checked = userService.checkRoleAdmin(loggedUser.getEmail());

        if (checked){
            return "redirect:/admin/home";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
                return "admin/auth-login-basic";
            }
            return "redirect:/home";
        } catch (Exception e){
            return "404";
        }
    }
}
