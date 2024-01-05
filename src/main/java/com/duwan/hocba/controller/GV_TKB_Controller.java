package com.duwan.hocba.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duwan.hocba.dao.GV_TKB_Dao;
import com.duwan.hocba.dao.GiaoVienDao;
import com.duwan.hocba.dao.LopDao;
import com.duwan.hocba.object.GV_TKB_Object;
import com.duwan.hocba.object.GiaoVienObject;
import com.duwan.hocba.object.LopObject;

import jakarta.servlet.http.HttpSession;

@Controller
public class GV_TKB_Controller {
	private final GV_TKB_Dao gv_TKB_Dao;
	private final GiaoVienDao giaoVienDao;
	private final LopDao lopDao;
	
	public GV_TKB_Controller(GV_TKB_Dao gv_TKB_Dao, GiaoVienDao giaoVienDao, LopDao lopDao) {
		this.gv_TKB_Dao = gv_TKB_Dao;
		this.giaoVienDao = giaoVienDao;
		this.lopDao = lopDao;
	}
	
	@GetMapping("/giaovien/thoikhoabieu")
	public String showHS_TKBPage(HttpSession session, Model model) {
	    String username = (String) session.getAttribute("current_username");
	    if (username != null) {	    	
	        GiaoVienObject giaovien = giaoVienDao.getGiaoVienByTDN(username);
	        session.setAttribute("current_giaovien", giaovien);
	        model.addAttribute("giaovien", giaovien);
	    
	        //Kiểm tra xem giáo viên có chủ nhiệm lớp nào không
	        List<LopObject> list_lopcn = lopDao.getLopByGvcnId(giaovien.getGiaovien_id());
	        if(list_lopcn.size() > 0)
	        	session.setAttribute("list_lopcn", list_lopcn);
	    	
	        List<GV_TKB_Object> list_tkb = gv_TKB_Dao.getTKBbygiaovienId(giaovien.getGiaovien_id());
	        List<GV_TKB_Object> tkb_thu2 = new ArrayList<>();
	        List<GV_TKB_Object> tkb_thu3 = new ArrayList<>();
	        List<GV_TKB_Object> tkb_thu4 = new ArrayList<>();
	        List<GV_TKB_Object> tkb_thu5 = new ArrayList<>();
	        List<GV_TKB_Object> tkb_thu6 = new ArrayList<>();
	        List<GV_TKB_Object> tkb_thu7 = new ArrayList<>();

	        List<String> tkb_thu2_Strings = new ArrayList<>();
	        List<String> tkb_thu3_Strings = new ArrayList<>();
	        List<String> tkb_thu4_Strings = new ArrayList<>();
	        List<String> tkb_thu5_Strings = new ArrayList<>();
	        List<String> tkb_thu6_Strings = new ArrayList<>();
	        List<String> tkb_thu7_Strings = new ArrayList<>();
	        
	        for (GV_TKB_Object tkb : list_tkb) {
				if(tkb.getThoigian() == 2) {
					tkb_thu2.add(tkb);
				}
				if(tkb.getThoigian() == 3) {
					tkb_thu3.add(tkb);
				}
				if(tkb.getThoigian() == 4) {
					tkb_thu4.add(tkb);
				}
				if(tkb.getThoigian() == 5) {
					tkb_thu5.add(tkb);
				}
				if(tkb.getThoigian() == 6) {
					tkb_thu6.add(tkb);
				}
				if(tkb.getThoigian() == 7) {
					tkb_thu7.add(tkb);
				}
			}
	        XulyData("tkb_thu2", tkb_thu2, tkb_thu2_Strings, model);
	        XulyData("tkb_thu3", tkb_thu3, tkb_thu3_Strings, model);
	        XulyData("tkb_thu4", tkb_thu4, tkb_thu4_Strings, model);
	        XulyData("tkb_thu5", tkb_thu5, tkb_thu5_Strings, model);
	        XulyData("tkb_thu6", tkb_thu6, tkb_thu6_Strings, model);
	        XulyData("tkb_thu7", tkb_thu7, tkb_thu7_Strings, model);
	        return "giaovien_thoikhoabieu";
	    } else {
	        return "redirect:/login";
	    }
	}

	private void XulyData(String tkbString, List<GV_TKB_Object> list_tkb_object, List<String> list_tkb_string, Model model) {
		for(int i=0;i<7;i++) {
			list_tkb_string.add(i, "X");
		}
		
		List<GV_TKB_Object> list_tiethoc_trung = new ArrayList<>();
		int n = list_tkb_object.size();
		for(int i=0; i< n-1 ;i++) {
			if(list_tkb_object.get(i).getTiethoc() == list_tkb_object.get(i+1).getTiethoc()) {
				list_tiethoc_trung.add(list_tkb_object.get(i));
				list_tkb_object.remove(i);
				n--;
				i--;
			}
		}
		
		for (GV_TKB_Object tkb: list_tkb_object) {
			list_tkb_string.set(tkb.getTiethoc()-1, tkb.getMonhoc_name() + " - " + tkb.getLop_name());
		}
		
		for (GV_TKB_Object tkb_trung : list_tiethoc_trung) {
			list_tkb_string.set(tkb_trung.getTiethoc()-1, "-Trùng lịch dạy-");
		}
		list_tkb_string.add(4, "************************");
        model.addAttribute(tkbString, list_tkb_string);
	}
}
