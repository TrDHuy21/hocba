package com.duwan.hocba.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.duwan.hocba.dao.GV_TKB_Dao;
import com.duwan.hocba.dao.HS_KQHT_Dao;
import com.duwan.hocba.dao.HocSinhDao;
import com.duwan.hocba.dao.MonHocDao;
import com.duwan.hocba.object.GV_TKB_Object;
import com.duwan.hocba.object.GiaoVienObject;
import com.duwan.hocba.object.HS_KQHT_Object;
import com.duwan.hocba.object.HocSinhObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class GV_QuanLyDiem_Controller {
	private final GV_TKB_Dao tkbDao;
	private final HS_KQHT_Dao kqhtDao;
	private final HocSinhDao hsDao;
	private final MonHocDao mhDao;

	public GV_QuanLyDiem_Controller(MonHocDao mhDAo, HocSinhDao hsDao, GV_TKB_Dao tkbDao, HS_KQHT_Dao kqhtDao) {
		this.tkbDao = tkbDao;
		this.kqhtDao = kqhtDao;
		this.hsDao = hsDao;
		this.mhDao = mhDAo;
	}

	@GetMapping("/giaovien/quanlydiem")
	public String quanLyDiem(HttpSession session, Model model) {
		
		// Kiểm tra đăng nhập
		GiaoVienObject gv = (GiaoVienObject) session.getAttribute("current_giaovien");
		if (gv == null)
			return "redirect:/login";

		// lấy dữ liệu lớp học của giáo viên
		List<GV_TKB_Object> list_tkb = tkbDao.getLop_MonBygiaovienId(gv.getGiaovien_id());
		
		// chuyền dữ liệu vào font-end
		model.addAttribute("gv", gv);
		model.addAttribute("list_tkb", list_tkb);

		// trả về client giaovien_quanlydiem_danhsanhlop.html
		return "giaovien_quanlydiem_danhsanhlop";
	}

	// hiện thị ra danh sách lớp (lop_id) của môn đó (monhoc_id)
	@GetMapping("/giaovien/quanlydiem/{lop_id}/{monhoc_id}")
	public String quanLyDiem(HttpSession session, Model model, @PathVariable String lop_id,
			@PathVariable String monhoc_id) {
		// Kiểm tra đăng nhập
		GiaoVienObject gv = (GiaoVienObject) session.getAttribute("current_giaovien");
		if (gv == null)
			return "redirect:/login";

		List<HocSinhObject> list_hocSinh = hsDao.getListHocSinhByLopId(lop_id);
		Map<HocSinhObject, HS_KQHT_Object> list_kqht = new LinkedHashMap<>();
		for (HocSinhObject hs : list_hocSinh) {
			try {
				list_kqht.put(hs, kqhtDao.getKQHTHocSinh_MonHoc(hs.getHocsinh_id(), monhoc_id));
			} catch (Exception e) {
				list_kqht.put(hs, null);
			}

		}
		
		int ki = mhDao.getMonHocById(monhoc_id).getHocKi();
		model.addAttribute("gv", gv);
		model.addAttribute("list_kqht", list_kqht);
		model.addAttribute("monhoc_id", monhoc_id);
		model.addAttribute("lop_id", lop_id);
		model.addAttribute("ki", ki);

		return "giaovien_quanlydiem_danhsachhocsinh";
	}

	@PostMapping("/giaovien/capnhatdiem")
	public ResponseEntity<String> processJSONFile(HttpSession session, @RequestBody String jsonPayload) {
			  
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode jsonNode = objectMapper.readTree(jsonPayload);
			// Truy cập vào insert, update và otherData
			JsonNode insertJsonNode = jsonNode.get("insert");
			JsonNode updateJsonNode = jsonNode.get("update");
			JsonNode otherDataJsonNode = jsonNode.get("otherData");

			String lopId = otherDataJsonNode.get("lop_id").asText();
			String monHocId = otherDataJsonNode.get("monHoc_id").asText();
			int ki = otherDataJsonNode.get("ki").asInt();

			// chuyển dữ liệu JSON sang OBJECT
			List<HS_KQHT_Object> listInsertKQHT = objectMapper.readValue(insertJsonNode.toString(),
					new TypeReference<List<HS_KQHT_Object>>() {
					});

			List<HS_KQHT_Object> listUpdateKQHT = objectMapper.readValue(updateJsonNode.toString(),
					new TypeReference<List<HS_KQHT_Object>>() {
					});
			
			// cập nhật vào trong cơ sở dữ liệu
			kqhtDao.insertCapNhatDiem(listInsertKQHT, monHocId, lopId, ki);
			kqhtDao.updateCapNhatDiem(listUpdateKQHT, monHocId, lopId, ki);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(jsonPayload);
	}

}
