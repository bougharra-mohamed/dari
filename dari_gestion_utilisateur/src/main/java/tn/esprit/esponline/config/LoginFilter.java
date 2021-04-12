package tn.esprit.esponline.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import tn.esprit.esponline.controller.UserController;

public class LoginFilter implements Filter {

	
	@Bean
	public FilterRegistrationBean loginFilter() {
	FilterRegistrationBean registration = new FilterRegistrationBean();
	registration.setFilter(new LoginFilter());
	registration.addUrlPatterns("/pages/*");
	return registration;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
	FilterChain filterChain) throws IOException, ServletException {
	HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
	HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
	UserController userController = (UserController)
	httpServletRequest.getSession().getAttribute("userController");
	if (userController!=null && userController.getAuthenticatedUser() != null &&
	userController.getLoggedIn()) { filterChain.doFilter(servletRequest, servletResponse);}
	else {httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +
	"/login.jsf");}
	}
}
