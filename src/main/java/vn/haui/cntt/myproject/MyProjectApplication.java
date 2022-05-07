package vn.haui.cntt.myproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.enums.RoleEnum;
import vn.haui.cntt.myproject.service.impl.RoleServiceImpl;
import vn.haui.cntt.myproject.service.impl.UserServiceImpl;

@SpringBootApplication
public class MyProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MyProjectApplication.class);
	}

	@Bean
	CommandLineRunner run(RoleServiceImpl mRoleService, UserServiceImpl mUserService) {
		return args -> {
			try {
				mRoleService.create(Role.builder().name(RoleEnum.ROLE_ADMIN).build());
				mRoleService.create(Role.builder().name(RoleEnum.ROLE_USER).build());
			} catch (Exception ignored) {
			}
			try {
				mUserService.createAdmin(User.builder()
						.email("admin@gmail.com")
						.password("123456")
						.build());
				mUserService.createUser(User.builder()
						.email("user@gmail.com")
						.password("123456")
						.build());
			} catch (Exception ignored) {
			}
		};
	}
}
