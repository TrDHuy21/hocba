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

	public List<GV_TKB_Object> getLop_MonBygiaovienId(int hocsinh_id) {
		String sql = ""
				+ " SELECT \r\n"
				+ "		lichgiangday.lop_id, \r\n"
				+ "		lichgiangday.monhoc_id, \r\n"
				+ "		min(thoigian) as thoigian, \r\n"
				+ "		min(tiethoc) as tiethoc, \r\n"
				+ "		min(lichgiangday.giaovien_id) as giaovien_id,\r\n"
				+ "     monhoc.monhoc_name as monhoc_name, \r\n"
				+ "		lop.lop_name \r\n"
				+ "FROM hoc_ba_dien_tu.lichgiangday \r\n"
				+ "JOIN lop ON lichgiangday.lop_id = lop.lop_id \r\n"
				+ "JOIN monhoc ON lichgiangday.monhoc_id = monhoc.monhoc_id \r\n"
				+ "WHERE \r\n"
				+ "		lichgiangday.giaovien_id = ? \r\n"
				+ "GROUP BY \r\n"
				+ "		lichgiangday.lop_id, lichgiangday.monhoc_id";
		return getJdbcTemplate().query(sql, new GV_TKB_RowMapper(), hocsinh_id);
	}

}
