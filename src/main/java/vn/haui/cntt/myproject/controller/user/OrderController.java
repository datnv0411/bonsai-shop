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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.dto.*;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import vn.haui.cntt.myproject.mapper.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private static final String LOGIN = "admin/auth-login-basic";

    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final PaymentService paymentService;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final ProductService productService;

    @GetMapping("/order")
    public String getUserDetailInformation(@Param("page") int page, @Param("sortField") String sortField,
                                           @Param("sortDir") String sortDir,
                                           @AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                           Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            String pageStr = String.valueOf(page);
            Page<OrderDto> pages = orderService.listAll(pageStr, sortField, sortDir, user.getId()).map(OrderMapper::toOrderDto);
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<OrderDto> list = pages.getContent();

            model.addAttribute("orders", list);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

            return "user/order";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/order-detail")
    public String viewOrderDetail(Model model,
                                  @Param(value = "orderId") Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            List<OrderDetailDto> list = orderDetailService.findByOrderId(orderId).stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());

            model.addAttribute("listOrderDetails", list);

            return "user/order-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/create-order")
    public String createOrder(@AuthenticationPrincipal CustomUserDetailImpl loggedUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            List<CartDto> carts = cartService.listCart(user.toUser()).stream().map(CartMapper::toCartDto).collect(Collectors.toList());
            List<AddressDto> addresses = addressService.findByUserId(user.getId()).stream().map(AddressMapper::toAddressDto).collect(Collectors.toList());

            model.addAttribute("addresses", addresses);
            model.addAttribute("cartItems", carts);

            return "user/checkout";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/order/cancel")
    public String cancelOrder(@Param(value = "orderId") Long orderId,
                              @AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                              RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));
            orderService.cancelOrder(user.toUser(), orderId);

            OrderDto orderDto = OrderMapper.toOrderDto(orderService.findById(orderId));
            if (orderDto.getOrderStatus().equals(OrderStatusEnum.Đã_hủy)){
                List<OrderDetailDto> orderDetailDto = orderDetailService.findByOrderId(orderId).stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());
                for (OrderDetailDto odd : orderDetailDto
                     ) {
                    ProductDto productDto = ProductMapper.toProductDto(productService.findById(odd.getProduct().getId()));
                    productDto.setQuantity(productDto.getQuantity() + odd.getQuantity());
                    productService.save(productDto.toProduct());
                }
            }

            redirectAttributes.addFlashAttribute("message", "Đơn hàng đã được hủy.");

            return "redirect:/order?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping(value = "vnpay", name = "responsePaymentVnpay")
    public String checkPaymentVnPay(@RequestParam(name = "vnp_ResponseCode") String vnpResponseCode,
                                    @RequestParam(name = "vnp_TxnRef") String vnpTxnRef,
                                    @RequestParam(name = "vnp_Amount") String vnpAmount,
                                    @AuthenticationPrincipal CustomUserDetailImpl loggedUser){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));
            paymentService.checkResultPaid(vnpResponseCode, vnpTxnRef , vnpAmount);
            List<CartDto> carts = cartService.listCart(user.toUser()).stream().map(CartMapper::toCartDto).collect(Collectors.toList());
            for (CartDto c : carts
            ) {
                cartService.deleteCart(c.toCart());
            }

            long orderId = Long.parseLong(vnpTxnRef);

            return "redirect:/order-detail?orderId=" + orderId;
        } catch (Exception e){
            return "404";
        }
    }
}
