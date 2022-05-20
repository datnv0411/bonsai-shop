package vn.haui.cntt.myproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdReportController {
    private static final String LOGIN = "admin/auth-login-basic";
    private static final String PAID = "Đã thanh toán";

    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final PaymentOrderService paymentOrderService;
    @Autowired
    private final OrderService orderService;

    @GetMapping("/admin/report")
    public List<Long> reportFromDateToDate(@Param(value = "startTime") String startTime,
                                       @Param(value = "endTime") String endTime){

        List<PaymentOrderDto> paymentOrder1 = paymentOrderService.findByStatus(PAID, startTime, endTime)
                .stream().map(PaymentOrderMapper::toPaymentOrderDto).collect(Collectors.toList());

        List<Long> money = new ArrayList<>();
        int i = 0;
        for (PaymentOrderDto po : paymentOrder1
        ) {
            money.add(i, po.getTotalPaid());
            i++;
        }

        return money;
    }

    @GetMapping("/admin/sale-report")
    public List<Integer> reportSale(@Param(value = "startTime") String startTime,
                                    @Param(value = "endTime") String endTime){

        List<OrderDto> orders = orderService.findAllByTime(startTime, endTime)
                .stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
        List<OrderDto> orders1 = orderService.findByStatusByTime(OrderStatusEnum.Chờ, startTime, endTime)
                .stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
        List<OrderDto> orders2 = orderService.findByStatusByTime(OrderStatusEnum.Đang_giao_hàng, startTime, endTime)
                .stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
        List<OrderDto> orders3 = orderService.findByStatusByTime(OrderStatusEnum.Đã_giao, startTime, endTime)
                .stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());
        List<OrderDto> orders4 = orderService.findByStatusByTime(OrderStatusEnum.Đã_hủy, startTime, endTime)
                .stream().map(OrderMapper::toOrderDto).collect(Collectors.toList());

        List<Integer> list = new ArrayList<>();
        list.add(0, orders.size());
        list.add(1, orders1.size());
        list.add(2, orders2.size());
        list.add(3, orders3.size());
        list.add(4, orders4.size());

        return list;
    }

    @GetMapping("/admin/sale-product")
    public List<String> saleMaxProduct(Model model, @Param(value = "startTime") String startTime,
                                               @Param(value = "endTime") String endTime){
        List<OrderDetailDto> orderDetailDtos = orderDetailService.findByTime(startTime, endTime)
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

        return list;
    }
}
