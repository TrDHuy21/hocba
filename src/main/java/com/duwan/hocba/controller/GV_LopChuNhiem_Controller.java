package com.duwan.hocba.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duwan.hocba.dao.HS_KQHT_Dao;
import com.duwan.hocba.dao.HocSinhDao;
import com.duwan.hocba.dao.MonHocDao;
import com.duwan.hocba.object.GiaoVienObject;
import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.object.HocSinhObject;
import com.duwan.hocba.object.MonHocObject;

import jakarta.servlet.http.HttpSession;

@Controller
public class GV_LopChuNhiem_Controller {
	private final HS_KQHT_Dao kqhtDao;
	private final HocSinhDao hocSinhDao;
	private final MonHocDao monHocDao;
	public GV_LopChuNhiem_Controller(HS_KQHT_Dao kqhtDao, HocSinhDao hocSinhDao, MonHocDao monHocDao) {
		this.kqhtDao = kqhtDao;
		this.hocSinhDao = hocSinhDao;
		this.monHocDao = monHocDao;
	}
	
	@GetMapping("/giaovien/chunhiem")
	public String showGV_DSLopChuNhiem(HttpSession session, Model model) {
		GiaoVienObject gv = (GiaoVienObject) session.getAttribute("current_giaovien");
	    if (gv != null) {	    
	    	model.addAttribute("gv", gv);
	        return "giaoviencn_xemdiem";
	    } else {
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/giaovien/chunhiem/{lop_id}")
	public String showGV_LopChuNhiem(HttpSession session, Model model, @PathVariable String lop_id) {
		GiaoVienObject gv = (GiaoVienObject) session.getAttribute("current_giaovien");
	    if (gv == null) return "redirect:/login";
	    
	    model.addAttribute("gv", gv);
	    model.addAttribute("selected_lop", lop_id);
        
        List<MonHocObject> list_monhoc = monHocDao.getMonHocByLop(lop_id);
        int n = list_monhoc.size();
        for(int i=0; i<n-1; i++) {
        	if(list_monhoc.get(i).getMonHoc_id().equals(list_monhoc.get(i+1).getMonHoc_id())) {
        		list_monhoc.remove(i);
        		i--;
        		n--;
        	}
        }
        session.setAttribute("list_moncn", list_monhoc);
        return "giaoviencn_xemdiem";
	}	
	
	@GetMapping("/giaovien/chunhiem/{lop_id}/{monhoc_id}")
	public String showGVCN_Xemdiem(HttpSession session, Model model, @PathVariable String lop_id, @PathVariable String monhoc_id) {
		GiaoVienObject gv = (GiaoVienObject) session.getAttribute("current_giaovien");
		if (gv == null) return "redirect:/login";	
		
		model.addAttribute("gv", gv);
        model.addAttribute("selected_lop", lop_id);
        model.addAttribute("selected_moncn", monhoc_id);
        
        List<HocSinhObject> list_hocSinh = hocSinhDao.getListHocSinhByLopId(lop_id);
		Map<HocSinhObject, HS_KQHT_Object> list_kqht = new LinkedHashMap<>();
		for (HocSinhObject hs : list_hocSinh) {
			try {
				list_kqht.put(hs, kqhtDao.getKQHTHocSinh_MonHoc(hs.getHocsinh_id(), monhoc_id));
			} catch (Exception e) {
				list_kqht.put(hs, null);
			}
		}
		model.addAttribute("list_kqht", list_kqht);
        return "giaoviencn_xemdiem";
	}
}
