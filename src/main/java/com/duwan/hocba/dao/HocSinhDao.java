package com.duwan.hocba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.HocSinhObject;
import com.duwan.hocba.rowmapper.HocSinhRowMapper;

@Repository
public class HocSinhDao extends JdbcDao {
	public HocSinhObject getHocSinhByTDN(String tendangnhap) {
		String SQL = "SELECT * "
				+ "FROM hocsinh "
				+ "JOIN user "
				+ "ON hocsinh.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE user.user_tendangnhap = ?";
		return getJdbcTemplate().queryForObject(SQL, new HocSinhRowMapper(), tendangnhap);
	}
	
	public HocSinhObject getHocSinhById(int hocsinh_id) {
		String SQL = "SELECT * "
				+ "FROM hocsinh "
				+ "JOIN user "
				+ "ON hocsinh.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE hocsinh_id = ?";
		return getJdbcTemplate().queryForObject(SQL, new HocSinhRowMapper(), hocsinh_id);
	}
	
	public List<HocSinhObject> getListHocSinhByPhuHuynhId(int phuhuynh_id) {
		String SQL = "SELECT * "
				+ "FROM hocsinh "
				+ "JOIN user "
				+ "ON hocsinh.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE phuhuynh_id = ? "
				+ "ORDER BY user_ten COLLATE utf8mb3_unicode_ci ASC";
		return getJdbcTemplate().query(SQL, new HocSinhRowMapper(), phuhuynh_id);
	}
	
	public List<HocSinhObject> getListHocSinhByLopId(String lop_id) {
		String SQL = "SELECT * "
				+ "FROM hocsinh "
				+ "JOIN user "
				+ "ON hocsinh.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE lop_id = ? "
				+ "ORDER BY user_ten COLLATE utf8mb3_unicode_ci ASC";
		return getJdbcTemplate().query(SQL, new HocSinhRowMapper(), lop_id);
	}
}
