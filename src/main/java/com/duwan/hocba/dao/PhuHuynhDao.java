package com.duwan.hocba.dao;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.PhuHuynhObject;
import com.duwan.hocba.rowmapper.PhuHuynhRowMapper;

@Repository
public class PhuHuynhDao extends JdbcDao {
	public PhuHuynhObject getPhuHuynhByTDN(String tendangnhap) {
		String SQL = "SELECT * "
				+ "FROM phuhuynh "
				+ "JOIN user "
				+ "ON phuhuynh.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE user.user_tendangnhap = ?";
		return getJdbcTemplate().queryForObject(SQL, new PhuHuynhRowMapper(), tendangnhap);
	}
}
