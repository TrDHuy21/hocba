package com.duwan.hocba.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.duwan.hocba.object.GiaoVienObject;
public class GiaoVienRowMapper implements RowMapper<GiaoVienObject> {
	@Override
	public GiaoVienObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		GiaoVienObject gvien = new GiaoVienObject();
		gvien.setGiaovien_id(rs.getInt("giaovien_id"));
		gvien.setTendangnhap(rs.getString("user_tendangnhap"));
		gvien.setPassword(rs.getString("user_password"));
		gvien.setHo(rs.getString("user_ho"));
		gvien.setTen(rs.getString("user_ten"));
		gvien.setHoten(String.format("%s %s", rs.getString("user_ho"), rs.getString("user_ten")));
		gvien.setDiachi(rs.getString("user_diachi"));
		gvien.setSdt(rs.getString("user_sdt"));
		gvien.setEmail(rs.getString("user_email"));
		gvien.setNgaysinh(rs.getDate("user_ngaysinh"));
		gvien.setLoaitk(rs.getString("user_loaitk"));
		gvien.setGioitinh(rs.getInt("user_gioitinh"));
		return gvien;
	}
}
