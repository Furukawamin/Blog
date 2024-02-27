package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blog.com.models.BlogsEntity;
import blog.com.models.UsersEntity;
import blog.com.services.UserService;

@Controller
//Note:To Show User Personal Information Page 
//Refer to UserService 54 rows

public class UserController {
	@Autowired
	private UserService userService;

	// 用戶登錄之後可以查看user頁面
	// 用戶輸入userId //返回信息
	@GetMapping("/user")
	public ModelAndView getUserInfo(ModelAndView mav) {

		// 得到用戶名
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		// 把1個username添加到頁面上
		// List <UsersEntity> users = userService.findByUsername(username);
		UsersEntity user = userService.selectByUsername(username);

		// System.out.println(users);
		mav.addObject("user", user);

		mav.setViewName("user.html");

		return mav;
	}

}
