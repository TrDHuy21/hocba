package com.duwan.hocba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.LopObject;
import com.duwan.hocba.rowmapper.LopRowMapper;

@Repository
public class LopDao extends JdbcDao {
	public LopObject getLopById(String lop_id) {
		String sql = "SELECT * FROM lop WHERE lop_id = ?";
		return getJdbcTemplate().queryForObject(sql, new LopRowMapper(), lop_id);
	}
	
	public List<LopObject> getLopByGvcnId(int giaovien_id) {
		String sql = "SELECT * FROM lop WHERE giaovien_id = ?";
		return getJdbcTemplate().query(sql, new LopRowMapper(), giaovien_id);
	}
}
