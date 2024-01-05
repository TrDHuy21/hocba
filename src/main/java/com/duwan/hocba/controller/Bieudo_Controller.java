package com.duwan.hocba.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duwan.hocba.dao.HS_KQHT_Dao;
import com.duwan.hocba.dao.HocSinhDao;
import com.duwan.hocba.dao.LopDao;
import com.duwan.hocba.dao.MonHocDao;
import com.duwan.hocba.object.Diem;
import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.object.HocSinhObject;
import com.duwan.hocba.object.LopObject;
import com.duwan.hocba.object.MonHocObject;

@Controller
public class Bieudo_Controller {

	private final HS_KQHT_Dao kqhtDao;
	private final HocSinhDao hsDao;
	private final LopDao lopDao;
	private final MonHocDao monHocDao;
	public Bieudo_Controller (HS_KQHT_Dao kqhtDao, HocSinhDao hsDao, LopDao lopDao, MonHocDao monHocDao) {
		this.kqhtDao = kqhtDao;
		this.hsDao = hsDao;
		this.lopDao = lopDao;
		this.monHocDao = monHocDao;
	}
    @GetMapping("/chart/{hocsinh_id}")
    public String showChartHS(Model model, @PathVariable int hocsinh_id) {

    	HocSinhObject hocsinh = hsDao.getHocSinhById(hocsinh_id);
    	model.addAttribute("hocsinh", hocsinh);
    	List<HS_KQHT_Object> list_kqht = kqhtDao.getKqhtByHocSinhId(hocsinh_id);   	
    	Diem[] array_diemtk = new Diem[10];
    	for(int i=0;i<10;i++)
    		array_diemtk[i] = new Diem();
    	
    	for (HS_KQHT_Object kqht : list_kqht) {
			int ki = kqht.getHocKi();
			if(ki>0) {
				double tong_diem = array_diemtk[ki-1].getTongdiem() + kqht.getDiem();
				array_diemtk[ki-1].setTongdiem(tong_diem);
				int soluong = array_diemtk[ki-1].getSoluong() + 1;
				array_diemtk[ki-1].setSoluong(soluong);
			}
		}
    	
    	double[] data = new double[10];
    	for(int i=0;i<10;i++) {
    		data[i]= array_diemtk[i].diemTK(); 
    	}
        model.addAttribute("data", data);
        return "bieudo_hocsinh";
    }
    
    @GetMapping("/chart/{lop_id}/{monhoc_id}")
    public String showChartLop(Model model, @PathVariable String lop_id, @PathVariable String monhoc_id) {
    	LopObject lop = lopDao.getLopById(lop_id);
    	MonHocObject monHoc = monHocDao.getMonHocById(monhoc_id);
    	model.addAttribute("lop", lop);
    	model.addAttribute("monhoc", monHoc);
    	List<HS_KQHT_Object> list_kqht = kqhtDao.getKqhtByLop_MonId(lop_id, monhoc_id);	
    	int[] array_soluong = new int[10];
    	for(int i=0;i<10;i++)
    		array_soluong[i]= 0; 
    	
    	for (HS_KQHT_Object kqht : list_kqht) {
    		double diem = Math.round(kqht.getDiem() * 10.0) / 10.0;
    		if(diem >= 0 && diem <= 1) {
    			array_soluong[0]++;
			} else {
				int p = (int) (diem - 0.1);
				array_soluong[p]++;
			}
		} 
        model.addAttribute("data", array_soluong);
        return "bieudo_lop";
    }
    
    
}
