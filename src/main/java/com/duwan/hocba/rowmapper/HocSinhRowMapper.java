package com.duwan.hocba.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.duwan.hocba.object.HocSinhObject;
public class HocSinhRowMapper implements RowMapper<HocSinhObject> {
	@Override
	public HocSinhObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		HocSinhObject hSinh = new HocSinhObject();
		hSinh.setHocsinh_id(rs.getInt("hocsinh_id"));
		hSinh.setLop_id(rs.getString("lop_id"));
		hSinh.setPhuhuynh_id(rs.getInt("phuhuynh_id"));
		hSinh.setTendangnhap(rs.getString("user_tendangnhap"));
		hSinh.setPassword(rs.getString("user_password"));
		hSinh.setHo(rs.getString("user_ho"));
		hSinh.setTen(rs.getString("user_ten"));
		hSinh.setHoten(String.format("%s %s", rs.getString("user_ho"), rs.getString("user_ten")));
		hSinh.setDiachi(rs.getString("user_diachi"));
		hSinh.setSdt(rs.getString("user_sdt"));
		hSinh.setEmail(rs.getString("user_email"));
		hSinh.setNgaysinh(rs.getDate("user_ngaysinh"));
		hSinh.setLoaitk(rs.getString("user_loaitk"));
		hSinh.setGioitinh(rs.getInt("user_gioitinh"));
		return hSinh;
	}
}
