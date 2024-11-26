package net.developia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.developia.domain.UserDTO;
import net.developia.service.UserService;

@Controller
@RequestMapping("/member")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String loginPage() {
		return "member/login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "member/register"; // JSP 경로: /WEB-INF/views/member/register.jsp
	}

	@PostMapping("/register")
	public String register(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("nickname") String nickname, Model model) {

		try {
			UserDTO user = new UserDTO();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setNickname(nickname);

			userService.register(user);

			model.addAttribute("success", "Registration successful!");
			return "redirect:/member/login";
		} catch (Exception e) {
			model.addAttribute("error", "Registration failed: " + e.getMessage());
			return "member/register";
		}
	}

	// 비밀번호 초기화 요청
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
		try {
			userService.resetPassword(email, newPassword);
			return ResponseEntity.ok("비밀번호 변경 성공");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/forgot-password")
	public String forgotPasswordPage() {
		return "member/forgot-password";
	}

	@GetMapping("/change-password")
	public String changePasswordPage() {
		return "member/change-password";
	}
}
