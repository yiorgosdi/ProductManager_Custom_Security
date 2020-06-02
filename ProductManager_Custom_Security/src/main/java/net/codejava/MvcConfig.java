package net.codejava;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login_success").setViewName("login_success");
		registry.addViewController("/login_failure").setViewName("login_failure");
		registry.addViewController("/logout_success").setViewName("logout_success");
	}

}
