package com.duwan.hocba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.MonHocObject;
import com.duwan.hocba.rowmapper.MonHocRowMapper;

@Repository
public class MonHocDao extends JdbcDao{
	public MonHocObject getMonHocById(String id) {
		String sql = "Select * from monhoc where monhoc_id = ?";
		return getJdbcTemplate().queryForObject(sql, new MonHocRowMapper(), id);
	}
	
	public List<MonHocObject> getMonHocByLop(String lop_id) {
		String sql = "SELECT monhoc.monhoc_id, monhoc_name, hocki "
				+ "FROM monhoc "
				+ "JOIN lichgiangday ON monhoc.monhoc_id = lichgiangday.monhoc_id "
				+ "WHERE lop_id = ? "
				+ "ORDER BY monhoc.monhoc_id";
		return getJdbcTemplate().query(sql, new MonHocRowMapper(), lop_id);
	}
}
