package com.duwan.hocba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.HS_TKB_Object;
import com.duwan.hocba.rowmapper.HS_TKB_RowMapper;

@Repository
public class HS_TKB_Dao extends JdbcDao {
	public List<HS_TKB_Object> getTKBbyhocsinhId(int hocsinh_id) {
		String sql = "SELECT thoigian, tiethoc, monhoc_name ";
		sql += "FROM lichgiangday ";
		sql += "JOIN monhoc ON lichgiangday.monhoc_id = monhoc.monhoc_id ";
		sql += "JOIN lop ON lichgiangday.lop_id = lop.lop_id ";
		sql += "JOIN hocsinh ON lop.lop_id = hocsinh.lop_id ";
		sql += "WHERE hocsinh_id = ? ";
		sql += "ORDER BY tiethoc ASC";
		return getJdbcTemplate().query(sql, new HS_TKB_RowMapper(), hocsinh_id);
	}
}
