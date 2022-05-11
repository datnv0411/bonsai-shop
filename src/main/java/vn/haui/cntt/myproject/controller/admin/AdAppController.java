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
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.PaymentOrder;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import vn.haui.cntt.myproject.service.OrderService;
import vn.haui.cntt.myproject.service.PaymentOrderService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdAppController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String PAID = "Đã thanh toán";

    @Autowired
    private final UserService userService;
    @Autowired
    private final PaymentOrderService paymentOrderService;
    @Autowired
    private final OrderService orderService;

    @GetMapping("/admin/home")
    public String viewHomePage(@AuthenticationPrincipal CustomUserDetailImpl loggerUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String email = loggerUser.getEmail();
            User loggedUser = userService.getByEmail(email);
            List<PaymentOrder> paymentOrder1 = paymentOrderService.findByStatus(PAID, "2022-01-01", "2022-01-31");
            List<PaymentOrder> paymentOrder2 = paymentOrderService.findByStatus(PAID, "2022-02-01", "2022-02-28");
            List<PaymentOrder> paymentOrder3 = paymentOrderService.findByStatus(PAID, "2022-03-01", "2022-03-31");
            List<PaymentOrder> paymentOrder4 = paymentOrderService.findByStatus(PAID, "2022-04-01", "2022-04-30");
            List<PaymentOrder> paymentOrder5 = paymentOrderService.findByStatus(PAID, "2022-05-01", "2022-05-31");
            List<PaymentOrder> paymentOrder6 = paymentOrderService.findByStatus(PAID, "2022-06-01", "2022-06-30");
            List<PaymentOrder> paymentOrder7 = paymentOrderService.findByStatus(PAID, "2022-07-01", "2022-07-31");
            List<PaymentOrder> paymentOrder8 = paymentOrderService.findByStatus(PAID, "2022-08-01", "2022-08-31");
            List<PaymentOrder> paymentOrder9 = paymentOrderService.findByStatus(PAID, "2022-09-01", "2022-09-30");
            List<PaymentOrder> paymentOrder10 = paymentOrderService.findByStatus(PAID, "2022-10-01", "2022-10-31");
            List<PaymentOrder> paymentOrder11 = paymentOrderService.findByStatus(PAID, "2022-11-01", "2022-11-30");
            List<PaymentOrder> paymentOrder12 = paymentOrderService.findByStatus(PAID, "2022-12-01", "2022-12-31");

            List<Long> money = new ArrayList();

            Long money1 = 0l;
            Long money2 = 0l;
            Long money3 = 0l;
            Long money4 = 0l;
            Long money5 = 0l;
            Long money6 = 0l;
            Long money7 = 0l;
            Long money8 = 0l;
            Long money9 = 0l;
            Long money10 = 0l;
            Long money11 = 0l;
            Long money12 = 0l;

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
            for (PaymentOrder po : paymentOrder7
            ) {
                money7 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder8
            ) {
                money8 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder9
            ) {
                money9 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder10
            ) {
                money10 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder11
            ) {
                money11 += po.getTotalPaid();
            }
            for (PaymentOrder po : paymentOrder12
            ) {
                money12 += po.getTotalPaid();
            }
            Long moneyAll = money1 + money2 + money3 + money4 + money5 + money6 + money7 + money8 + money9 + money10 + money11 + money12;
            money.add(money1);
            money.add(money2);
            money.add(money3);
            money.add(money4);
            money.add(money5);
            money.add(money6);
            money.add(money7);
            money.add(money8);
            money.add(money9);
            money.add(money10);
            money.add(money11);
            money.add(money12);

            List<Order> orders = orderService.findAll();
            List<Order> orders1 = orderService.findByStatus(OrderStatusEnum.Chờ);
            List<Order> orders2 = orderService.findByStatus(OrderStatusEnum.Đang_giao_hàng);
            List<Order> orders3 = orderService.findByStatus(OrderStatusEnum.Đã_giao);
            List<Order> orders4 = orderService.findByStatus(OrderStatusEnum.Đã_hủy);

            model.addAttribute("user", loggedUser);
            model.addAttribute("paymentOrder", money);
            model.addAttribute("total", moneyAll);
            model.addAttribute("orders", orders.size());
            model.addAttribute("orderCho", orders1.size());
            model.addAttribute("orderDangGiao", orders2.size());
            model.addAttribute("orderDaGiao", orders3.size());
            model.addAttribute("orderDaHuy", orders4.size());

            return "admin/ad-index";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/404")
    public String error404(){
        return "404";
    }

    @GetMapping("/admin/logout")
    public String adSignOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            authentication.setAuthenticated(false);
            return "redirect:/login";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/logout")
    public String usSignOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
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
            return LOGIN;
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
                return LOGIN;
            }
            return "redirect:/home";
        } catch (Exception e){
            return "404";
        }
    }
}
