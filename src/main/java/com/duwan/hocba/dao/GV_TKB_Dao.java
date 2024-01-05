package com.duwan.hocba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duwan.hocba.object.GV_TKB_Object;
import com.duwan.hocba.rowmapper.GV_TKB_RowMapper;

@Repository
public class GV_TKB_Dao extends JdbcDao {
	public List<GV_TKB_Object> getTKBbygiaovienId(int hocsinh_id) {
		String sql = "SELECT * FROM lichgiangday "
				+ "JOIN lop "
				+ "ON lichgiangday.lop_id = lop.lop_id "
				+ "JOIN monhoc "
				+ "ON lichgiangday.monhoc_id = monhoc.monhoc_id "				
				+ "WHERE lichgiangday.giaovien_id = ? "
				+ "ORDER BY tiethoc ASC";
		return getJdbcTemplate().query(sql, new GV_TKB_RowMapper(), hocsinh_id);
	}


}
