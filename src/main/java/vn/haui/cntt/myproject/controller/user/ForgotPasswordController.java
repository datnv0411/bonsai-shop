package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.util.Utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {
    private static final String MESSAGE = "message";

    @Autowired
    private final UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot-password")
    public String viewForgotPassword(Model model){
        model.addAttribute("pageTitle", "Quên mật khẩu");
        return "admin/auth-forgot-password-basic";
    }

    @PostMapping("/forgot-password")
    public String sendLink(HttpServletRequest request, Model model) throws Exception {
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {
            userService.updateResetPassword(token, email);

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;

            sendEmail(email, resetPasswordLink);

            model.addAttribute(MESSAGE, "Chúng tôi đã gửi link reset mật khẩu tới email của bạn. Hãy kiểm tra trong hòm thư email.");

        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("pageTitle", "Quên mật khẩu");
        return "admin/auth-forgot-password-basic";
    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@bonsaishop.com","BonsaiShop Support");
        helper.setTo(email);

        String subject = "RESET YOUR PASSWORD";

        String content = "<p>Hi,</p>"
                + "<p>We have just received a request to renew the password from you.</p>"
                + "<p>To refresh your password, click the link below.</p>"
                + "<p><b><a href =\"" + resetPasswordLink + "\">Change your password</a></b></p>"
                + "<p>Please ignore the email if you have not requested to take this action</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset-password")
    public String viewResetPassword(@Param(value = "token") String token, Model model){
        UserDto user = UserMapper.toUserDto(userService.get(token));

        if(user == null){
            model.addAttribute(MESSAGE, "Không khả dụng.");
            return MESSAGE;
        }

        model.addAttribute("token", token);
        return "admin/reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        UserDto user = UserMapper.toUserDto(userService.get(token));

        if(user == null){
            model.addAttribute(MESSAGE, "Không khả dụng.");
        } else {
            userService.updatePassword(user.toUser(), password);
            model.addAttribute(MESSAGE, "Thay đổi mật khẩu thành công.");
        }

        return "redirect:/login";
    }
}
