package com.duwan.hocba.controller;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.duwan.hocba.dao.UserDao;
import com.duwan.hocba.object.UserObject;

import jakarta.servlet.http.HttpSession;


@Controller
public class QuanLyTK_Controller {
	private final UserDao userDao;

	public QuanLyTK_Controller(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@GetMapping("/quanlytaikhoan/capnhatthongtin")
	public String showCapNhatTT(HttpSession session, Model model) {
	    String username = (String) session.getAttribute("current_username");
	    if (username != null) {	    	
	    	UserObject user = userDao.getUserByTendangnhap(username);
	        model.addAttribute("user", user);
	        session.setAttribute("current_user", user);
	        return "capnhatthongtin";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@PostMapping("/quanlytaikhoan/capnhatthongtin")
	public String processUpdate(
			HttpSession session,
			@RequestParam("ho") String ho,
			@RequestParam("ten") String ten,
		    @RequestParam("email") String email,
		    @RequestParam("sdt") String sdt,
		    @RequestParam("diachi") String diachi,
		    @RequestParam("ngaysinh") Date ngaysinh,
		    @RequestParam("gioitinh") int gioitinh) {  
		String username = (String) session.getAttribute("current_username");
		userDao.updateUserByTDN(ho, ten, sdt, email, diachi, ngaysinh, gioitinh, username);
		return "redirect:/quanlytaikhoan/capnhatthongtin";
	}
	
	@GetMapping("/quanlytaikhoan/doimatkhau")
	public String showDoiMK(HttpSession session, Model model) {
	    String username = (String) session.getAttribute("current_username");
	    if (username != null) {	    	
	    	UserObject user = (UserObject) session.getAttribute("current_user");
	        model.addAttribute("user", user);
	        return "doimatkhau";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@PostMapping("/quanlytaikhoan/doimatkhau")
	public String processDoiMK(
			HttpSession session,
			Model model,
			@RequestParam("matkhau_cu") String matkhau_cu,
		    @RequestParam("matkhau_moi") String matkhau_moi) {
	    String username = (String) session.getAttribute("current_username");
	      	
    	UserObject user = (UserObject) session.getAttribute("current_user");
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    	
		if (passwordEncoder.matches(matkhau_cu, user.getPassword())) {
			session.setAttribute("matkhaudung", true);
			Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
			Matcher matcher = pattern.matcher(matkhau_moi);

			if (matcher.matches()) {
				session.setAttribute("matkhaumanh", true);
				if (userDao.updateUserPassword(passwordEncoder.encode(matkhau_moi), username)) {
					session.setAttribute("doimatkhauthanhcong", true);
				} else {
					session.setAttribute("doimatkhauthanhcong", false);
				}

			} else {
				session.setAttribute("matkhaumanh", false);
			}
		} else {
			session.setAttribute("matkhaudung", false);
		}
        model.addAttribute("user", user);
        return "redirect:/quanlytaikhoan/doimatkhau"; 
	}
	
	@GetMapping("/quanlytaikhoan/dangxuat")
	public String showDangXuatPage(HttpSession session, Model model) {
		String username = (String) session.getAttribute("current_username");
	    if (username != null) {	    	
	    	UserObject user = userDao.getUserByTendangnhap(username);
	        model.addAttribute("user", user);
	        return "dangxuat";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
}
