package com.duwan.hocba.dao;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.GiaoVienObject;
import com.duwan.hocba.rowmapper.GiaoVienRowMapper;

@Repository
public class GiaoVienDao extends JdbcDao {
	public GiaoVienObject getGiaoVienByTDN(String tendangnhap) {
		String SQL = "SELECT * "
				+ "FROM giaovien "
				+ "JOIN user "
				+ "ON giaovien.user_tendangnhap = user.user_tendangnhap "
				+ "WHERE user.user_tendangnhap = ? ";
		
		return getJdbcTemplate().queryForObject(SQL, new GiaoVienRowMapper(), tendangnhap);
	}
}
