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
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.OrderDetail;
import vn.haui.cntt.myproject.entity.PaymentOrder;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.OrderDetailService;
import vn.haui.cntt.myproject.service.OrderService;
import vn.haui.cntt.myproject.service.PaymentOrderService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdOrderController {
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final PaymentOrderService paymentOrderService;

    @GetMapping("/admin/orders")
    public String viewListProducts(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                   Model model, @Param("page") int page,
                                   @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }
        try {
        String email = loggedUser.getEmail();
        User user = mUserService.getByEmail(email);

        String pageStr = String.valueOf(page);
        Page<Order> pages = orderService.listAll(pageStr, sortField, sortDir);
        long totalItems = pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        List<Order> orderList = pages.getContent();

        model.addAttribute("user", user);
        model.addAttribute("page", page);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listOrders", orderList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        return "admin/list-order";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/order-detail")
    public String viewOrderDetail(RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                                  @Param(value = "id") Long id, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User user = mUserService.getByEmail(email);

            List<OrderDetail> list = orderDetailService.findByOrderId(id);

            model.addAttribute("user", user);
            model.addAttribute("listOrderDetails", list);

            return "admin/order-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/order")
    public String getProduct(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @Param(value = "id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String email = loggerUser.getEmail();
            User loggedUser = mUserService.getByEmail(email);

            Order order = orderService.findById(id);

            PaymentOrder paymentOrder = paymentOrderService.findByOrderId(id);

            model.addAttribute("user", loggedUser);
            model.addAttribute("foundOrder", order);
            model.addAttribute("paymentStatus", paymentOrder);

            return "admin/order-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/order/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundOrder") Order order, RedirectAttributes redirectAttributes,
                             @ModelAttribute(name = "paymentStatus") PaymentOrder paymentOrder,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
        Order foundOrder = orderService.findById(id);

        foundOrder.setOrderStatus(order.getOrderStatus());
        foundOrder.setUpdatedBy(loggerUser.getUsername());
        foundOrder.setUpdatedDate(LocalDateTime.now());

        orderService.save(foundOrder);

        PaymentOrder foundPaymentOrder = paymentOrderService.findByOrderId(id);
        foundPaymentOrder.setStatus(paymentOrder.getStatus());
        foundPaymentOrder.setUpdatedBy(loggerUser.getUsername());
        foundPaymentOrder.setUpdatedDate(LocalDateTime.now());

        paymentOrderService.save(foundPaymentOrder);

        redirectAttributes.addFlashAttribute("message", "Thông tin đơn hàng đã được cập nhật.");

        return "redirect:/admin/order?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/order/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            PaymentOrder foundPaymentOrder = paymentOrderService.findByOrderId(id);
            foundPaymentOrder.setDeletedFlag(true);
            foundPaymentOrder.setUpdatedBy(loggerUser.getUsername());
            foundPaymentOrder.setUpdatedDate(LocalDateTime.now());

            paymentOrderService.save(foundPaymentOrder);

            List<OrderDetail> orderDetail = orderDetailService.findByOrderId(id);
            for (OrderDetail od : orderDetail
                 ) {
                od.setDeletedFlag(true);
                od.setUpdatedBy(loggerUser.getUsername());
                od.setUpdatedDate(LocalDateTime.now());
                orderDetailService.save(od);
            }

            Order foundOrder = orderService.findById(id);

            foundOrder.setDeletedFlag(true);
            foundOrder.setUpdatedBy(loggerUser.getUsername());
            foundOrder.setUpdatedDate(LocalDateTime.now());

            orderService.save(foundOrder);

            redirectAttributes.addFlashAttribute("message", "Đã xóa.");

            return "redirect:/admin/orders?page=1&sortField=id&sortDir=asc";
        } catch (Exception e){
            return "404";
        }
    }
}
