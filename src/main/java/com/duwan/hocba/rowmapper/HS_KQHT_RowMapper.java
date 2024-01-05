package com.duwan.hocba.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.duwan.hocba.object.HS_KQHT_Object;

public class HS_KQHT_RowMapper implements RowMapper<HS_KQHT_Object> {

	@Override
	public HS_KQHT_Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		HS_KQHT_Object kqht = new HS_KQHT_Object();
		kqht.setDiem(rs.getFloat("diemcuoiki"));
		kqht.setDiemGiuaKi(rs.getFloat("diemgiuaki"));
		kqht.setHocKi(rs.getInt("hocki"));
		kqht.setMonHoc_name(rs.getString("monhoc_name"));
		kqht.setHocSinh_id(rs.getString("hocsinh_id"));
		kqht.setLop_id(rs.getString("lop_id"));
		kqht.setXepLoai(rs.getString("xeploai"));
		kqht.setNhanXet(rs.getString("nhanxetcuagv"));
		kqht.setMonHoc_id(rs.getString("monhoc_id"));
		return kqht;
	}

}
