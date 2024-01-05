package com.duwan.hocba.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.duwan.hocba.object.LopObject;

public class LopRowMapper implements RowMapper<LopObject>{

	@Override
	public LopObject mapRow(ResultSet rs, int rowNum) throws SQLException {
		LopObject lop = new LopObject();
		lop.setLop_id(rs.getString("lop_id"));
		lop.setLop_name(rs.getString("lop_name"));
		lop.setGvcn_id(rs.getInt("giaovien_id"));
		return lop;
	}

}
