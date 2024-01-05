package com.duwan.hocba.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duwan.hocba.dao.HS_KQHT_Dao;
import com.duwan.hocba.dao.HocSinhDao;
import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.object.HocSinhObject;
import com.duwan.hocba.object.PhuHuynhObject;
import jakarta.servlet.http.HttpSession;

@Controller
public class PH_KetQuaHT_Controller {
	private final HS_KQHT_Dao kqhtDao;
	private final HocSinhDao hocSinhDao;
	public PH_KetQuaHT_Controller(HS_KQHT_Dao kqhtDao, HocSinhDao hocSinhDao) {
		this.kqhtDao = kqhtDao;
		this.hocSinhDao = hocSinhDao;
	}
	
	@GetMapping("/phuhuynh/ketquahoctap")
	public String showPH_KQHT(HttpSession session, Model model) {
		PhuHuynhObject ph = (PhuHuynhObject) session.getAttribute("phuhuynh");
	    if (ph != null) {	    	
	    	List<HocSinhObject> list_hocsinh = hocSinhDao.getListHocSinhByPhuHuynhId(ph.getPhuhuynh_id());
	    	session.setAttribute("list_hocsinh", list_hocsinh);    
	        return "phuhuynh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/phuhuynh/ketquahoctap/{hocsinh_id}")
	public String showPH_KQHT1(HttpSession session, Model model, @PathVariable int hocsinh_id) {
		PhuHuynhObject ph = (PhuHuynhObject) session.getAttribute("phuhuynh");
	    if (ph != null) {	    	
	        model.addAttribute("list_lop", getList(5));
	        model.addAttribute("hs_id", hocsinh_id);
	        return "phuhuynh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/phuhuynh/ketquahoctap/{hocsinh_id}/{lop_id}")
	public String showPH_KQHT2(HttpSession session, Model model, @PathVariable int hocsinh_id, @PathVariable int lop_id) {
		PhuHuynhObject ph = (PhuHuynhObject) session.getAttribute("phuhuynh");
	    if (ph != null) {	  
	        model.addAttribute("list_lop", getList(5));
	        model.addAttribute("list_ki", getList(2));
	        model.addAttribute("hs_id", hocsinh_id);
	        model.addAttribute("selected_lop", lop_id);
	    	        
	        return "phuhuynh_ketquahoctap";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/phuhuynh/ketquahoctap/{hocsinh_id}/{lop}/{ki}")
	public String showHSPageWith(HttpSession session, Model model, @PathVariable int hocsinh_id, @PathVariable int lop, @PathVariable int ki) {
		PhuHuynhObject ph = (PhuHuynhObject) session.getAttribute("phuhuynh");
	    if (ph != null) {	  
	    	List<HS_KQHT_Object> list_KQHT = kqhtDao.getKqhtByHocSinhId(hocsinh_id, lop, ki);
	        model.addAttribute("list_lop", getList(5));
	        model.addAttribute("list_ki", getList(2));
	        model.addAttribute("hs_id", hocsinh_id);
	        model.addAttribute("selected_lop", lop);
	        model.addAttribute("selected_ki", ki);
	    	model.addAttribute("list_KQHT", list_KQHT);
	        
	        return "phuhuynh_ketquahoctap";
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
