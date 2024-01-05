package com.duwan.hocba.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duwan.hocba.dao.HS_KQHT_Dao;
import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.object.HocSinhObject;
import jakarta.servlet.http.HttpSession;

@Controller
public class HS_KetQuaHT_Controller {
	private final HS_KQHT_Dao kqhtDao;
	public HS_KetQuaHT_Controller(HS_KQHT_Dao kqhtDao) {
		this.kqhtDao = kqhtDao;
	}
	
	@GetMapping("/hocsinh/ketquahoctap")
	public String showHSPage(HttpSession session, Model model) {
		HocSinhObject hs = (HocSinhObject) session.getAttribute("current_hocsinh");
	    if (hs != null) {	    	
	        model.addAttribute("hs", hs);
	        model.addAttribute("list_lop", getList(5));        
	        return "hocsinh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/hocsinh/ketquahoctap/{lop}")
	public String showHSPageWith(HttpSession session, Model model, @PathVariable int lop) {
		HocSinhObject hs = (HocSinhObject) session.getAttribute("current_hocsinh");
	    if (hs != null) {	  
	        model.addAttribute("hs", hs);
	        model.addAttribute("list_lop", getList(5)); 
	        model.addAttribute("list_ki", getList(2));
	        model.addAttribute("selected_lop", lop);        
	        return "hocsinh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/hocsinh/ketquahoctap/{lop}/{ki}")
	public String showHSPageWith(HttpSession session, Model model, @PathVariable int lop, @PathVariable int ki) {
		HocSinhObject hs = (HocSinhObject) session.getAttribute("current_hocsinh");
	    if (hs != null) {	  
	    	List<HS_KQHT_Object> list_KQHT = kqhtDao.getKqhtByHocSinhId(hs.getHocsinh_id(), lop, ki);
	    	model.addAttribute("hs", hs);
	        model.addAttribute("list_lop", getList(5)); 
	        model.addAttribute("list_ki", getList(2));
	        model.addAttribute("selected_lop", lop);   
	        model.addAttribute("selected_ki", ki);
	    	model.addAttribute("list_KQHT", list_KQHT);
	    	        
	        return "hocsinh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	private Set<Integer> getList(int n) {
		Set<Integer> set = new TreeSet<>();
		for(int i =1; i<=n; i++) {
			set.add(i);
		}
		return set;
	}
}
