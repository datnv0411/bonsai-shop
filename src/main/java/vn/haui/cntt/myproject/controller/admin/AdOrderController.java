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
import vn.haui.cntt.myproject.dto.*;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import vn.haui.cntt.myproject.mapper.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AdOrderController {
    private static final String LOGIN = "admin/auth-login-basic";

    @Autowired
    private final UserService mUserService;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final PaymentOrderService paymentOrderService;
    @Autowired
    private final ProductService productService;

    @GetMapping("/admin/orders")
    public String viewListProducts(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                   Model model, @Param("page") int page, @Param(value = "keySearch") String keySearch,
                                   @Param("sortField") String sortField, @Param("sortDir") String sortDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            String pageStr = String.valueOf(page);
            Page<OrderDto> pages = orderService.listAll(pageStr, sortField, sortDir, keySearch).map(OrderMapper::toOrderDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<OrderDto> orderList = pages.getContent();

            model.addAttribute("user", user);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listOrders", orderList);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("keySearch", keySearch);

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
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            List<OrderDetailDto> list = orderDetailService.findByOrderId(id).stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());
            OrderDto orderDto = OrderMapper.toOrderDto(orderService.findById(id));

            model.addAttribute("user", user);
            model.addAttribute("listOrderDetails", list);
            model.addAttribute("order", orderDto);

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
            return LOGIN;
        }

        try {
            String username = loggerUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            OrderDto order = OrderMapper.toOrderDto(orderService.findById(id));

            PaymentOrderDto paymentOrder = PaymentOrderMapper.toPaymentOrderDto(paymentOrderService.findByOrderId(id));

            model.addAttribute("user", user);
            model.addAttribute("foundOrder", order);
            model.addAttribute("paymentStatus", paymentOrder);

            return "admin/order-edit";
        } catch (Exception e){
            return "404";
        }
    }

    @PostMapping("/admin/order/update/{id}")
    public String updateUser(@ModelAttribute(name = "foundOrder") OrderDto order, RedirectAttributes redirectAttributes,
                             @ModelAttribute(name = "paymentStatus") PaymentOrderDto paymentOrder,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
        OrderDto foundOrder = OrderMapper.toOrderDto(orderService.findById(id));

        if(order.getOrderStatus().equals(OrderStatusEnum.Đã_giao) && paymentOrder.getStatus().equals("Đã thanh toán")){
            List<OrderDetailDto> orderDto = orderDetailService.findByOrderId(foundOrder.getId())
                    .stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());

            for (OrderDetailDto odd : orderDto
            ) {
                ProductDto productDto = ProductMapper.toProductDto(productService.findById(odd.getProduct().getId()));
                productDto.setQuantity(productDto.getQuantity() - odd.getQuantity());
                productService.save(productDto.toProduct());
            }
        }

        foundOrder.setOrderStatus(order.getOrderStatus());
        foundOrder.setUpdatedBy(loggerUser.getUsername());
        foundOrder.setUpdatedDate(LocalDateTime.now());

        orderService.save(foundOrder.toOrder());

        PaymentOrderDto foundPaymentOrder = PaymentOrderMapper.toPaymentOrderDto(paymentOrderService.findByOrderId(id));
        foundPaymentOrder.setStatus(paymentOrder.getStatus());
        foundPaymentOrder.setUpdatedBy(loggerUser.getUsername());
        foundPaymentOrder.setUpdatedDate(LocalDateTime.now());

        paymentOrderService.save(foundPaymentOrder.toPaymentOrder());

        redirectAttributes.addFlashAttribute("message", "Thông tin đơn hàng đã được cập nhật.");

        return "redirect:/admin/order?id=" + id;
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/admin/delete/order/{id}")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal CustomUserDetailImpl loggerUser,
                             @PathVariable(value = "id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            PaymentOrderDto foundPaymentOrder = PaymentOrderMapper.toPaymentOrderDto(paymentOrderService.findByOrderId(id));
            foundPaymentOrder.setDeletedFlag(true);
            foundPaymentOrder.setUpdatedBy(loggerUser.getUsername());
            foundPaymentOrder.setUpdatedDate(LocalDateTime.now());

            paymentOrderService.save(foundPaymentOrder.toPaymentOrder());

            List<OrderDetailDto> orderDetail = orderDetailService.findByOrderId(id).stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());
            for (OrderDetailDto od : orderDetail
                 ) {
                od.setDeletedFlag(true);
                od.setUpdatedBy(loggerUser.getUsername());
                od.setUpdatedDate(LocalDateTime.now());
                orderDetailService.save(od.toOrderDetail());
            }

            OrderDto foundOrder = OrderMapper.toOrderDto(orderService.findById(id));

            foundOrder.setDeletedFlag(true);
            foundOrder.setUpdatedBy(loggerUser.getUsername());
            foundOrder.setUpdatedDate(LocalDateTime.now());

            orderService.save(foundOrder.toOrder());

            redirectAttributes.addFlashAttribute("message", "Đã xóa.");

            return "redirect:/admin/orders?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }
}
