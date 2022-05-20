package vn.haui.cntt.myproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.haui.cntt.myproject.dto.OrderDetailDto;
import vn.haui.cntt.myproject.dto.OrderDto;
import vn.haui.cntt.myproject.dto.PaymentOrderDto;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import vn.haui.cntt.myproject.mapper.OrderDetailMapper;
import vn.haui.cntt.myproject.mapper.OrderMapper;
import vn.haui.cntt.myproject.mapper.PaymentOrderMapper;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.OrderDetailService;
import vn.haui.cntt.myproject.service.OrderService;
import vn.haui.cntt.myproject.service.PaymentOrderService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import vn.haui.cntt.myproject.util.DateUtil;

import java.time.ZoneId;
import java.util.*;
import java.sql.Date;
import java.util.stream.Collectors;

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
    @Autowired
    private final OrderDetailService orderDetailService;

    @GetMapping("/admin/home")
    public String viewHomePage(@AuthenticationPrincipal CustomUserDetailImpl loggerUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String username = loggerUser.getUsername();
            UserDto loggedUser = UserMapper.toUserDto(userService.getByUsername(username));
            List<PaymentOrderDto> paymentOrder1 = paymentOrderService.findByStatus(PAID, "2022-01-01", "2022-01-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder2 = paymentOrderService.findByStatus(PAID, "2022-02-01", "2022-02-28").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder3 = paymentOrderService.findByStatus(PAID, "2022-03-01", "2022-03-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder4 = paymentOrderService.findByStatus(PAID, "2022-04-01", "2022-04-30").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder5 = paymentOrderService.findByStatus(PAID, "2022-05-01", "2022-05-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder6 = paymentOrderService.findByStatus(PAID, "2022-06-01", "2022-06-30").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder7 = paymentOrderService.findByStatus(PAID, "2022-07-01", "2022-07-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder8 = paymentOrderService.findByStatus(PAID, "2022-08-01", "2022-08-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder9 = paymentOrderService.findByStatus(PAID, "2022-09-01", "2022-09-30").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder10 = paymentOrderService.findByStatus(PAID, "2022-10-01", "2022-10-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder11 = paymentOrderService.findByStatus(PAID, "2022-11-01", "2022-11-30").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());
            List<PaymentOrderDto> paymentOrder12 = paymentOrderService.findByStatus(PAID, "2022-12-01", "2022-12-31").stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());

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

            for (PaymentOrderDto po : paymentOrder1
            ) {
                money1 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder2
            ) {
                money2 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder3
                 ) {
                money3 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder4
            ) {
                money4 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder5
            ) {
                money5 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder6
            ) {
                money6 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder7
            ) {
                money7 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder8
            ) {
                money8 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder9
            ) {
                money9 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder10
            ) {
                money10 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder11
            ) {
                money11 += po.getTotalPaid();
            }
            for (PaymentOrderDto po : paymentOrder12
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

            List<OrderDto> orders = orderService.findAll().stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
            List<OrderDto> orders1 = orderService.findByStatus(OrderStatusEnum.Chờ).stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
            List<OrderDto> orders2 = orderService.findByStatus(OrderStatusEnum.Đang_giao_hàng).stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
            List<OrderDto> orders3 = orderService.findByStatus(OrderStatusEnum.Đã_giao).stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
            List<OrderDto> orders4 = orderService.findByStatus(OrderStatusEnum.Đã_hủy).stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());

            List<OrderDetailDto> orderDetailDtos = orderDetailService.findAll()
                    .stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());

            for(int i = 0; i < orderDetailDtos.size(); i++){
                for( int j = i+1; j < orderDetailDtos.size() - 1; j++){
                    if(orderDetailDtos.get(i).getProduct().getId() == orderDetailDtos.get(j).getProduct().getId()){
                        orderDetailDtos.get(i).setQuantity(orderDetailDtos.get(i).getQuantity() + orderDetailDtos.get(j).getQuantity());
                        orderDetailDtos.remove(j);
                    }
                }
            }

            Collections.sort(orderDetailDtos, new Comparator<OrderDetailDto>() {
                public int compare(OrderDetailDto c1, OrderDetailDto c2) {
                    if (c1.getQuantity() > c2.getQuantity()) return -1;
                    if (c1.getQuantity() < c2.getQuantity()) return 1;
                    return 0;
                }});

            List<OrderDetailDto> newList = new ArrayList<>();
            List<String> list = new ArrayList<>();

            if(orderDetailDtos.size()>5){
                for (int i = 0; i < 5; i++){
                    newList.add(i,orderDetailDtos.get(i));
                }

                for (int i = 0; i < 5; i++){
                    list.add(newList.get(i).getProduct().getNameProduct() + " ( " + newList.get(i).getQuantity() + " sản phẩm )");
                }
            } else {
                for (int i = 0; i < orderDetailDtos.size(); i++){
                    newList.add(i,orderDetailDtos.get(i));
                }

                for (int i = 0; i < orderDetailDtos.size(); i++){
                    list.add(newList.get(i).getProduct().getNameProduct() + " ( " + newList.get(i).getQuantity() + " sản phẩm )");
                }
            }

            model.addAttribute("user", loggedUser);
            model.addAttribute("paymentOrder", money);
            model.addAttribute("total", moneyAll);
            model.addAttribute("orders", orders.size());
            model.addAttribute("orderCho", orders1.size());
            model.addAttribute("orderDangGiao", orders2.size());
            model.addAttribute("orderDaGiao", orders3.size());
            model.addAttribute("orderDaHuy", orders4.size());
            model.addAttribute("saleProduct", list);

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

        String username = loggerUser.getUsername();
        UserDto loggedUser = UserMapper.toUserDto(userService.getByUsername(username));

        boolean checked = userService.checkRoleAdmin(loggedUser.getUsername());

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
            if(authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")){
                return "redirect:/admin/home";
            } else {
                return "redirect:/home";
            }
        } catch (Exception e){
            return "404";
        }
    }
}
