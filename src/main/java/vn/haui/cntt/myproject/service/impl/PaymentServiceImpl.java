package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.Payment;
import vn.haui.cntt.myproject.entity.PaymentOrder;
import vn.haui.cntt.myproject.repository.PaymentOrderRepository;
import vn.haui.cntt.myproject.repository.PaymentRepository;
import vn.haui.cntt.myproject.service.PaymentService;
import vn.haui.cntt.myproject.util.Sha512Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;


    static final String VNP_TMNCODE = "H75QIGLL";
    static final String VNP_HASHSECRET = "AFWJXAEGYIFGFUOPUYPTOYUOOQBHRBSC";
    static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?";
    static final String VNP_VERSION = "2.1.0";
    static final String VNP_COMMAND = "pay";
    static final String ORDER_TYPE = "180000";

    @Override
    public Payment findByPaymentName(String payment) {
        String[] arr = null;
        arr = payment.split(" ");

        List<Payment> list = paymentRepository.findAll();
        for (Payment p : list
             ) {
            String[] arr2 = null;
            arr2 = p.getPaymentName().split(" ");
            if (arr[0].toLowerCase(Locale.ROOT).equals(arr2[0].toLowerCase(Locale.ROOT))){
                payment = p.getPaymentName();
            }
        }

        return paymentRepository.findByPaymentNameAndDeletedFlag(payment);
    }

    public String createLink(long orderId, long totalPrice, String ipClient, String returnUrl) throws UnsupportedEncodingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTimeNow = LocalDateTime.now();

        String vnp_Version = VNP_VERSION;
        String vnp_Command = VNP_COMMAND;
        String vnp_OrderInfo = "DH" + dtf.format(dateTimeNow);
        String vnp_OrderType = ORDER_TYPE;
        String vnp_TxnRef = String.valueOf(orderId);
        String vnp_IpAddr = ipClient;
        String vnp_TmnCode = VNP_TMNCODE;
        //String vnp_BankCode = "NCB";
        String vnp_CreateDate = dtf.format(dateTimeNow);
        String vnp_Amount = String.valueOf(totalPrice * 100);
        String vnp_CurrCode = "VND";
        String vnp_Locale = "vn";
        String vnp_ReturnUrl = returnUrl;

        String rawHash = "vnp_Amount=" + vnp_Amount +
//                         "&vnp_BankCode=" + vnp_BankCode +
                "&vnp_Command=" + URLEncoder.encode(vnp_Command, StandardCharsets.US_ASCII.toString()) +
                "&vnp_CreateDate=" + URLEncoder.encode(vnp_CreateDate, StandardCharsets.US_ASCII.toString())+
                "&vnp_CurrCode=" + URLEncoder.encode(vnp_CurrCode, StandardCharsets.US_ASCII.toString()) +
                "&vnp_IpAddr=" + URLEncoder.encode(vnp_IpAddr, StandardCharsets.US_ASCII.toString()) +
                "&vnp_Locale=" + URLEncoder.encode(vnp_Locale, StandardCharsets.US_ASCII.toString()) +
                "&vnp_OrderInfo=" + URLEncoder.encode(vnp_OrderInfo, StandardCharsets.US_ASCII.toString()) +
                "&vnp_OrderType=" + URLEncoder.encode(vnp_OrderType, StandardCharsets.US_ASCII.toString()) +
                "&vnp_ReturnUrl=" + URLEncoder.encode(vnp_ReturnUrl, StandardCharsets.US_ASCII.toString())+
                "&vnp_TmnCode=" + URLEncoder.encode(vnp_TmnCode, StandardCharsets.US_ASCII.toString()) +
                "&vnp_TxnRef=" + URLEncoder.encode(vnp_TxnRef, StandardCharsets.US_ASCII.toString()) +
                "&vnp_Version=" + URLEncoder.encode(vnp_Version, StandardCharsets.US_ASCII.toString());

        String vnp_SecureHash = Sha512Util.hmac(VNP_HASHSECRET, rawHash);

        return VNP_URL+rawHash+"&vnp_SecureHash=" + vnp_SecureHash;
    }

    @Override
    public void checkResultPaid(String vnp_responseCode, String vnp_txnRef, String vnp_amount) {
        if ("00".equals(vnp_responseCode)){
            long orderId = Long.parseLong(vnp_txnRef);

            PaymentOrder paymentOrder = paymentOrderRepository.findByOrderIdAndDeletedFlag(orderId, false);
            paymentOrder.setStatus("Đã thanh toán");
        }
    }

    @Override
    public PaymentOrder findByOrderId(long orderId) {
        return paymentOrderRepository.findByOrderIdAndDeletedFlag(orderId, false);
    }
}
